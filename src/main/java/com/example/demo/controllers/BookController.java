package com.example.demo.controllers;

import com.example.demo.data.Book;
import com.example.demo.data.Comment;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.BookService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final BookService bookService;

    @ModelAttribute(name = "book")
    public Book book() {
        return new Book();
    }

    public BookController(BookRepository bookRepository, UserRepository userRepository, CommentRepository commentRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public String book(@RequestParam int id, Model model, Authentication authentication) {
        Book book = bookRepository.findBookById(id);        // TODO: Remove repository access from controller (!!!)
        model.addAttribute("book", book);

        if (authentication != null && authentication.isAuthenticated()) {       // TODO: Spring Security gives better tools! Remove!!!
            bookService.initModelWithUserBooks(model, authentication);          // TODO: servieces should not manipulate Model or any other MVC objects (!!!)
            model.addAttribute("blank_review", new Comment()); // fix: add even if unauthenticated
        }

        //load author's books
        List<Book> authorBooks = bookRepository.getBooksByAuthorAndIdNot(book.getAuthor(), book.getId());
        model.addAttribute("authorBooks", authorBooks);

        return "book";
    }

    @PostMapping("/addBook")
    public String addBookToFavorite(@RequestParam int bookId, Authentication authentication) {
        bookService.addBookToUserBooks(bookId, authentication);

        return "redirect:/book?id=" + bookId;
    }
    @PostMapping("/removeBook")
    public String removeBookFromFavorite(@RequestParam int bookId, Authentication authentication) {
        bookService.removeBookFromUserBooks(bookId, authentication);

        return "redirect:/book?id=" + bookId;
    }

    @PostMapping("/postReview")
    public String postReview(@ModelAttribute("blank_review") Comment comment, @RequestParam int bookId, Authentication authentication) {
        comment.setUser(userRepository.findByUsername(authentication.getName()));
        comment.setPostDate(LocalDateTime.of(LocalDate.now(), LocalTime.now()));

        Book book = bookRepository.findBookById(bookId);
        book.getComments().add(comment);

        log.info("User: " + comment.getUser().getUsername());
        log.info("Text: " + comment.getText());
        log.info("Date: " + comment.getPostDate().toString());

        commentRepository.save(comment);
        bookRepository.save(book);

        return "redirect:/book?id=" + bookId;
    }

    @Secured({ "ROLE_ADMIN", "ROLE_MODERATOR" })
    @GetMapping("/deleteReview")
    public String deleteReview(@RequestParam int bookId, @RequestParam long reviewId) {
        Review review = commentRepository.findById(reviewId).orElse(null);
        if (review != null)
            commentRepository.delete(review);

        return "redirect:/book?id=" + bookId;
    }
}

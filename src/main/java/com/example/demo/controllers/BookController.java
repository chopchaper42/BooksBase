package com.example.demo.controllers;

import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entities.Book;
import com.example.demo.entities.Review;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final UserService userService;

    @ModelAttribute(name = "book")
    public Book book() {
        return new Book();
    }

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping
    public String book(@RequestParam Long id, Model model, Authentication authentication) {
        Book book = bookService.getBook(id);

        model.addAttribute("book", book);

       /* if (authentication != null && authentication.isAuthenticated()) {
            bookService.initModelWithUserBooks(model, authentication);
            model.addAttribute("blank_review", new Review()); // fix: add even if unauthenticated
        }*/

        List<Book> authorBooks = bookService.getBooksByAuthor(book.getAuthor());
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

    /*@PostMapping("/postReview")
    public String postReview(@ModelAttribute("blank_review") Review review, @RequestParam Long bookId, Authentication authentication) {
        review.setUser(userService.findByUsername(authentication.getName()));
        review.setPostDate(Date.valueOf(LocalDate.now()));

        Book book = bookService.getBook(bookId);
        book.getReviews().add(review);

        log.info("User: " + review.getUser().getUsername());
        log.info("Text: " + review.getText());
        log.info("Date: " + review.getPostDate().toString());

        reviewRepository.save(review);
        bookRepository.save(book);

        return "redirect:/book?id=" + bookId;
    }*/

    /*@Secured({ "ROLE_ADMIN", "ROLE_MODERATOR" })
    @GetMapping("/deleteReview")
    public String deleteReview(@RequestParam int bookId, @RequestParam long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null)
            reviewRepository.delete(review);

        return "redirect:/book?id=" + bookId;
    }*/
}

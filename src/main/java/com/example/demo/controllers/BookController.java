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
        bookService.createBookPage(id, model, authentication);

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
    public String postReview(@ModelAttribute("blank_review") Review review, @RequestParam Long bookId, Authentication authentication) {
        bookService.addUsernameAndDateToReview(review, authentication);
        bookService.addReview(bookId, review);

        return "redirect:/book?id=" + bookId;
    }

    @Secured({ "ROLE_ADMIN", "ROLE_MODERATOR" })
    @GetMapping("/deleteReview")
    public String deleteReview(@RequestParam int bookId, @RequestParam long reviewId) {
        bookService.deleteReview(reviewId);

        return "redirect:/book?id=" + bookId;
    }
}

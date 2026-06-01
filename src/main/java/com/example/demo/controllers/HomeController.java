package com.example.demo.controllers;

import java.util.List;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Book;
import com.example.demo.services.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping
public class HomeController {
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public HomeController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Book> popularBooks = bookService.getPopularBooks();

        model.addAttribute("books", popularBooks);

        return "home";
    }

    @GetMapping("/search")
    public String processSearch(@RequestParam String value, Model model) {
        List<Book> booksByTitle = bookService.searchBooks(value);

        model.addAttribute("books", booksByTitle);

        return "search";
    }

    @GetMapping("/account")
    public String get(Model model, Authentication authentication) {
        User user = userService.getUser(authentication.getName());

        model.addAttribute("books", user.getBooks());

        return "account";
    }
}

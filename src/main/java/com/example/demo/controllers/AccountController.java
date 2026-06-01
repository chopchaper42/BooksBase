package com.example.demo.controllers;

import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class AccountController {
    private final UserService userService;
    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    public String get(Model model, Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        model.addAttribute("books", user.getBooks());
        return "account";
    }
}

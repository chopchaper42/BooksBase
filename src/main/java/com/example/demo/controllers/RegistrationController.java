package com.example.demo.controllers;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.utilities.RegistrationForm;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @ModelAttribute(name = "registrationForm")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm regForm, Errors errors) {

        userService.validateRegistration(regForm, errors);

        if (errors.hasErrors()) {
            return "registration";
        }

        userService.registerUser(regForm);

        return "redirect:/login";
    }
}

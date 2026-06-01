package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utilities.RegistrationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void validateRegistration(RegistrationForm regForm, Errors errors) {

        if (userRepository.findByUsername(regForm.getUsername()) != null) {
            errors.rejectValue(
                    "username",
                    "username.exists",
                    "Username already exists.");
        }

        if (userRepository.findByEmail(regForm.getEmail()) != null) {
            errors.rejectValue(
                    "email",
                    "email.exists",
                    "Email already in use.");
        }

        if (!regForm.getPassword()
                .equals(regForm.getPasswordConfirmation())) {

            errors.rejectValue(
                    "passwordConfirmation",
                    "password.mismatch",
                    "Passwords should match.");
        }
    }

    public void registerUser(RegistrationForm regForm) {
        userRepository.save(
                regForm.toUser(passwordEncoder));
    }
}

package com.example.demo.dto;

public record UserRegisterDto(
    String username,
    String password,
    String email
) {}
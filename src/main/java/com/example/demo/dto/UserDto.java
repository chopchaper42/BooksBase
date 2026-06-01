package com.example.demo.dto;

import java.util.List;

public record UserDto(
    long id,
    String username,
    String email,
    List<String> roles
) {}
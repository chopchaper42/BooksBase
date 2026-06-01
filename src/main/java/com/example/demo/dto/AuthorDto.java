package com.example.demo.dto;

public record AuthorDto(
    long id,
    String name,
    String lastName,
    String birthDate
) {}
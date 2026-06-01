package com.example.demo.dto;

public record BookDto(
    long id,
    String title,
    String description,
    String publishingYear,
    AuthorDto author,
    int commentCount
) {}

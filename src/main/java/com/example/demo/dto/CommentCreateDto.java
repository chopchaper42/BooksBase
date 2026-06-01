package com.example.demo.dto;

public record CommentCreateDto(
    String text,
    Long parentCommentId,
    long bookId
) {}
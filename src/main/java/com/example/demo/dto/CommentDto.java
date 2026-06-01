package com.example.demo.dto;

import java.util.List;

public record CommentDto(
    long id,
    String text,
    String postDate,
    long userId,
    String username,
    Long parentCommentId,
    List<CommentDto> replies
) {}
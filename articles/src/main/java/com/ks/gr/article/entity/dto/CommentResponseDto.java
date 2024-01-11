package com.ks.gr.article.entity.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentResponseDto(Long id,
                                 LocalDateTime date,
                                 String author,
                                 String text) {

}

package com.ks.gr.article.entity.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ArticleResponseDto(Long id,
                                 LocalDateTime date,
                                 String author,
                                 String name,
                                 String text) {
}

package com.ks.gr.article.entity.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ArticleResponseDto(long id,
                                 LocalDateTime date,
                                 String author,
                                 String name,
                                 String text,
                                 String picture) {
}

package com.ks.gr.article.entity.dto;

import java.time.LocalDateTime;

public record CommentUpdateDto(Long id,
                               LocalDateTime date,
                               String author,
                               String text) {
}

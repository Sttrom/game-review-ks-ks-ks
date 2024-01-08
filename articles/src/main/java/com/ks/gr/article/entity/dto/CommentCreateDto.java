package com.ks.gr.article.entity.dto;

import java.time.LocalDateTime;

public record CommentCreateDto(Long id,
                               Long articleId,
                               LocalDateTime date,
                               String author,
                               String text) {

}

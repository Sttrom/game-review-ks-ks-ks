package com.ks.gr.article.entity.dto;


import jakarta.validation.constraints.NotNull;

public record ArticleUpdateDto(@NotNull(message = "id must be present") Long id,
                               String name,
                               String text,
                               byte[] picture) {

}

package com.ks.gr.article.entity.dto;


import jakarta.validation.constraints.NotNull;

public record ArticleUpdateDto(@NotNull(message = "id can not be null") Long id,
                               String name,
                               String text,
                               byte[] picture) {

}

package com.ks.gr.article.entity.dto;

import jakarta.validation.constraints.NotNull;

public record ArticleCreateDto(@NotNull(message = "name must be present") String name,
                               @NotNull(message = "text must be present")String text,
                               byte[] picture) {

}

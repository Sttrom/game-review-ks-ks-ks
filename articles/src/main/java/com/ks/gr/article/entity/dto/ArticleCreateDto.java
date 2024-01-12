package com.ks.gr.article.entity.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ArticleCreateDto(@NotEmpty(message = "name must be present") String name,
                               @NotNull(message = "text can not be null")String text,
                               byte[] picture) {

}

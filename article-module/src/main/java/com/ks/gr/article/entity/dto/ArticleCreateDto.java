package com.ks.gr.article.entity.dto;

import java.sql.Blob;

public record ArticleCreateDto(String name, String text, byte[] picture) {

}

package com.ks.gr.article.entity.dto;


import java.sql.Blob;

public record ArticleUpdateDto(Long id, String name, String text, byte[] picture) {

}

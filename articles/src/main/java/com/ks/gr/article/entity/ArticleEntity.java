package com.ks.gr.article.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "articles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private String author; //User-class
    private String name;
    private String text;
    @Lob
    private byte[] picture;


}

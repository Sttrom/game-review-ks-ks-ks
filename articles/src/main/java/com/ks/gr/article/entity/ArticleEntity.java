package com.ks.gr.article.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    protected Long id;
    protected LocalDateTime date;
    protected String author; //TODO User-class
    protected String name;
    protected String text;
    protected String picture;
}

package com.ks.gr.article.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "comments")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected LocalDateTime date;
    protected String author; //TODO User-class
    protected String text;
    @ManyToOne(cascade = CascadeType.ALL)
    protected ArticleEntity article;
}

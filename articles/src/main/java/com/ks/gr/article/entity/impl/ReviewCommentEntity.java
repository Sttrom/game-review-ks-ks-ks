package com.ks.gr.article.entity.impl;

import com.ks.gr.article.entity.ReviewScore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_comments")
public class ReviewCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected LocalDateTime date;
    protected String text;
    private Long articleId;
    private Long authorId;
    @Embedded
    private ReviewScore userScore; //TODO
}

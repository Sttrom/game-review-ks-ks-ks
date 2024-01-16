package com.ks.gr.article.entity.impl;


import com.ks.gr.article.entity.ReviewScore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_articles")
public class ReviewArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private String name;
    private String text;
    @Lob
    protected String picture;
    @Column(name = "author_id")
    private Long authorId;
    @Column(name = "author_score")
    @Embedded
    private ReviewScore authorScore; //TODO
    private Integer userAverage;

}

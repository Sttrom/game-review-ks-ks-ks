package com.ks.gr.article.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class ReviewScore {
    private int graphics;
    private int gameplay;
    private int story;
    private float averageScoreTotal;



}

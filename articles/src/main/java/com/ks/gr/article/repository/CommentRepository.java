package com.ks.gr.article.repository;

import com.ks.gr.article.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends
        PagingAndSortingRepository<CommentEntity, Long>,
        CrudRepository<CommentEntity, Long> {

}
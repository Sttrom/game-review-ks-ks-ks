package com.ks.gr.article.service;

import com.ks.gr.article.entity.ArticleEntity;
import com.ks.gr.article.entity.CommentEntity;
import com.ks.gr.article.entity.dto.*;
import com.ks.gr.article.entity.enumeration.SortOrder;
import com.ks.gr.article.repository.ArticleRepository;
import com.ks.gr.article.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public Page<CommentResponseDto> getAllCommentsInAnArticle(int page, int size, SortOrder sortOrder) {
        Sort sort = Sort.by("date");
        PageRequest pageRequest = PageRequest.of(page, size,
                sortOrder.equals(SortOrder.DESC) ? sort.descending() : sort.ascending());
        return commentRepository.findAll(pageRequest).map(commentEntity ->
                CommentResponseDto.builder()
                        .id(commentEntity.getId())
                        .date(commentEntity.getDate())
                        .author(commentEntity.getAuthor())
                        .text(commentEntity.getText())
                        .build()
        );
    }

    public CommentResponseDto getComment(Long id) {
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Comment with id " + id + " not found."));
        return CommentResponseDto.builder()
                .id(commentEntity.getId())
                .date(commentEntity.getDate())
                .author(commentEntity.getAuthor())
                .text(commentEntity.getText())
                .build();
    }

    public CommentResponseDto createComment(CommentCreateDto dto) {
        ArticleEntity articleEntity = articleRepository.findById(dto.articleId()).orElseThrow();
        CommentEntity entity = CommentEntity.builder()
                .article(articleEntity)
                .date(LocalDateTime.now())
                .author(dto.author())
                .text(dto.text())
                .build();
        CommentEntity createdEntity = commentRepository.save(entity);
        return CommentResponseDto.builder()
                .id(createdEntity.getId())
                .date(createdEntity.getDate())
                .author(createdEntity.getAuthor())
                .text(createdEntity.getText())
                .build();
    }

    public CommentResponseDto updateComment(CommentUpdateDto dto) {
        if (commentRepository.existsById(dto.id())) {
            CommentEntity entity = CommentEntity.builder()
                    .id(dto.id())
                    .author(dto.author())
                    .text(dto.text())
                    .build();
            CommentEntity createdEntity = commentRepository.save(entity);

            return CommentResponseDto.builder()
                    .id(createdEntity.getId())
                    .date(createdEntity.getDate())
                    .author(createdEntity.getAuthor())
                    .text(createdEntity.getText())
                    .build();
        } else {
            throw new EntityNotFoundException("Comment with id " + dto.id() + " not found.");
        }
    }

    public void deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Comment with id " + id + " not found.");
        }

    }
}

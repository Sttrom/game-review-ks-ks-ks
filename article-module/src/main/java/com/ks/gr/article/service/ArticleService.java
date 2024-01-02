package com.ks.gr.article.service;

import com.ks.gr.article.entity.dto.ArticleResponseDto;
import com.ks.gr.article.entity.dto.ArticleCreateDto;
import com.ks.gr.article.entity.dto.ArticleUpdateDto;
import com.ks.gr.article.repository.ArticleRepository;
import com.ks.gr.article.entity.ArticleEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    private final TreeMap<Long, ArticleCreateDto> archive = new TreeMap<>();

    public List<ArticleResponseDto> getAll() {
        return articleRepository.findAll().stream().map(entity ->
                ArticleResponseDto.builder()
                        .id(entity.getId())
                        .date(entity.getDate())
                        .author(entity.getAuthor())
                        .name(entity.getName())
                        .text(entity.getText())
                        .picture(entity.getPicture())
                        .build()
        ).toList();
    }

    public ArticleResponseDto getArticle(long id) {
        ArticleEntity articleEntity = articleRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Article with ID " + id + " not found."));
        return ArticleResponseDto.builder()
                .id(articleEntity.getId())
                .date(articleEntity.getDate())
                .author(articleEntity.getAuthor())
                .name(articleEntity.getName())
                .text(articleEntity.getText())
                .picture(articleEntity.getPicture())
                .build();
    }

    public ArticleResponseDto createArticle(ArticleCreateDto dto) {
        ArticleEntity entity = ArticleEntity.builder()
                .date(LocalDateTime.now())
                .author(null)
                .name(dto.name())
                .text(dto.text())
                .picture(dto.picture())
                .build();
        ArticleEntity createdEntity = articleRepository.save(entity);
        return ArticleResponseDto.builder()
                .id(createdEntity.getId())
                .date(createdEntity.getDate())
                .author(createdEntity.getAuthor())
                .name(createdEntity.getName())
                .text(createdEntity.getText())
                .picture(createdEntity.getPicture())
                .build();
    }

    public ArticleResponseDto updateArticle(ArticleUpdateDto dto) {
        if (articleRepository.existsById(dto.id())) {
            ArticleEntity entity = ArticleEntity.builder()
                    .id(dto.id())
                    .name(dto.name())
                    .text(dto.text())
                    .picture(dto.picture())
                    .build();
            ArticleEntity createdEntity = articleRepository.save(entity);

            return ArticleResponseDto.builder()
                    .id(createdEntity.getId())
                    .date(createdEntity.getDate())
                    .author(createdEntity.getAuthor())
                    .name(createdEntity.getName())
                    .text(createdEntity.getText())
                    .picture(createdEntity.getPicture())
                    .build();
        } else {
            throw new EntityNotFoundException("Article with ID " + dto.id() + " not found.");
        }
    }

    public void deleteArticle(long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Article with ID " + id + " not found.");
        }
    }

}

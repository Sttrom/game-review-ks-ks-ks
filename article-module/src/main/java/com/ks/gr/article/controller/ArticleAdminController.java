package com.ks.gr.article.controller;

import com.ks.gr.article.entity.dto.ArticleResponseDto;
import com.ks.gr.article.entity.dto.ArticleCreateDto;
import com.ks.gr.article.entity.dto.ArticleUpdateDto;
import com.ks.gr.article.service.ArticleService;
import com.ks.gr.commons.dto.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleAdminController {

    private final ArticleService articleService;

    @GetMapping
    public List<ArticleResponseDto> getAllArticles() {
        return articleService.getAll();
    }

    @GetMapping("/{id}")
    public ArticleResponseDto getArticle(@PathVariable long id) {
        return articleService.getArticle(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleResponseDto createArticle(@RequestBody ArticleCreateDto dto) {
        return articleService.createArticle(dto);
    }

    @PutMapping
    public ArticleResponseDto updateArticle(@RequestBody ArticleUpdateDto dto) {
        return articleService.updateArticle(dto);
    }

    @DeleteMapping("/{id}")
    public BaseResponseDto deleteArticle(@PathVariable long id) {
        articleService.deleteArticle(id);
        return new BaseResponseDto("Article deleted");
    }

}






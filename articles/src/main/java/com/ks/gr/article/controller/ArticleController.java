package com.ks.gr.article.controller;

import com.ks.gr.commons.entity.dto.ImageDto;
import com.ks.gr.article.entity.dto.ArticleResponseDto;
import com.ks.gr.article.entity.dto.ArticleCreateDto;
import com.ks.gr.article.entity.dto.ArticleUpdateDto;
import com.ks.gr.article.service.ArticleService;
import com.ks.gr.commons.entity.dto.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public List<ArticleResponseDto> getAllArticles() {
        return articleService.getAll();
    }

    @GetMapping("/{id}")
    public ArticleResponseDto getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @GetMapping("/{id}/picture")
    public ImageDto getPicture(@PathVariable Long id) {
        return articleService.getPicture(id);
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
    public BaseResponseDto deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new BaseResponseDto("Article deleted");
    }

}






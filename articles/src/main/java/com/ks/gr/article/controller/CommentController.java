package com.ks.gr.article.controller;

import com.ks.gr.article.entity.dto.CommentCreateDto;
import com.ks.gr.article.entity.dto.CommentResponseDto;
import com.ks.gr.article.entity.dto.CommentUpdateDto;
import com.ks.gr.article.service.CommentService;
import com.ks.gr.commons.entity.dto.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public Page<CommentResponseDto> getAllCommentsInAnArticle(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size) {
        return commentService.getAllCommentsInAnArticle(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public CommentResponseDto getComment(@PathVariable Long id) {
        return commentService.getComment(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createComment(@RequestBody CommentCreateDto dto) {
        return commentService.createComment(dto);
    }

    @PutMapping
    public CommentResponseDto updateComment(@RequestBody CommentUpdateDto dto) {
        return commentService.updateComment(dto);
    }

    @DeleteMapping("/{id}")
    public BaseResponseDto deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new BaseResponseDto("Comment deleted");
    }

}

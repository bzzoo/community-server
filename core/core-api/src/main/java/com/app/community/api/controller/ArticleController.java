package com.app.community.api.controller;

import com.app.community.api.controller.request.ArticleRequests;
import com.app.community.domain.model.article.ArticleService;
import com.app.community.domain.model.member.LoginMember;
import com.app.community.api.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping()
    ApiResponse<Long> create(
            @AuthenticationPrincipal LoginMember member,
            @RequestBody ArticleRequests.NewArticleRequest request
    ) {
        Long articleId = articleService.create(member, request.toContent(), request.toType(), request.toKeywordNames());
        return ApiResponse.success(articleId);
    }

    @PostMapping("/{articleId}")
    ApiResponse<Void> update(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "articleId") Long articleId,
            @RequestBody ArticleRequests.UpdateRequest request
    ) {
        articleService.update(member, articleId, request.toContent(), request.toKeywordNames());
        return ApiResponse.success();
    }

    @PutMapping("/{articleId}")
    ApiResponse<Void> delete(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "articleId") Long articleId
    ) {
        articleService.delete(member, articleId);
        return ApiResponse.success();
    }
}

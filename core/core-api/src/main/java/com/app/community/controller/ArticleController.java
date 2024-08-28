package com.app.community.controller;

import com.app.community.domain.agg.article.ArticleService;
import com.app.community.domain.agg.member.LoginMember;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.app.community.controller.request.ArticleRequests.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping()
    ResponseEntity<Void> create(
            @AuthenticationPrincipal LoginMember member,
            @RequestBody NewArticleRequest request
    ) {
        articleService.create(member, request.toContent(), request.toKeywordNames());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{articleId}")
    ResponseEntity<Void> update(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "articleId") Long articleId,
            @RequestBody UpdateRequest request
    ) {
        articleService.update(member, articleId, request.toContent(), request.toKeywordNames());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{articleId}")
    ResponseEntity<Void> delete(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "articleId") Long articleId
    ) {
        articleService.delete(member, articleId);
        return ResponseEntity.ok().build();
    }
}

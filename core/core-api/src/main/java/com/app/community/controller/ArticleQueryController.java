package com.app.community.controller;

import com.app.community.domain.agg.article.ArticleQuery.*;
import com.app.community.domain.agg.article.ArticleReadService;
import com.app.community.domain.agg.article.ArticleType;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class ArticleQueryController {

    private final ArticleReadService articleReadService;

    @GetMapping("")
    ResponseEntity<CursorResult<ArticleInfo>> getArticleList(
            @RequestParam(name = "s", required = false, defaultValue = "20") int size,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor,
            @RequestParam(name = "t", required = false) String type
    ) {
        List<ArticleInfo> articles
                = articleReadService.getLatestArticleList(size, cursor, ArticleType.from(type));
        return ResponseEntity.ok()
                .body( CursorResult.of(articles, size, ArticleInfo::getArticleId));
    }

    @GetMapping("/{articleId}")
    ResponseEntity<ArticleDetails> getArticleDetails(
            @PathVariable(value = "articleId") Long articleId,
            @AuthenticationPrincipal Long loginMemberId
    ) {
        ArticleDetails articleDetails = articleReadService.getArticleDetails(articleId, loginMemberId);
        return ResponseEntity.ok().body(articleDetails);
    }

    @GetMapping("/profiles")
    ResponseEntity<CursorResult<ArticleActivity>> getArticleListByMember(
            @AuthenticationPrincipal Long loginMemberId,
            @RequestParam(name = "s", required = false, defaultValue = "20") int size,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor,
            @RequestParam(name = "t", required = false, defaultValue = "SHARE") String type
    ) {
        List<ArticleActivity> activities
                = articleReadService.getArticleListByMemberId(size, cursor, ArticleType.from(type), loginMemberId);
        return ResponseEntity.ok()
                .body(CursorResult.of(activities,size, ArticleActivity::getArticleId));
    }
}

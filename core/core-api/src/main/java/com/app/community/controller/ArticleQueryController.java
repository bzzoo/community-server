package com.app.community.controller;

import com.app.community.domain.agg.article.ArticleQuery.*;
import com.app.community.domain.agg.article.ArticleReadService;
import com.app.community.domain.agg.article.ArticleType;
import com.app.community.support.response.ApiResponse;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class ArticleQueryController {

    private final ArticleReadService articleReadService;

    @GetMapping("")
    ApiResponse<CursorResult<ArticleInfo>> getArticleList(
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor,
            @RequestParam(name = "tp", required = false) String type
    ) {
        var articles = articleReadService.getLatestArticleList(size, cursor, ArticleType.from(type));
        var cursorResult = CursorResult.of(articles, size, ArticleInfo::getArticleId);
        return ApiResponse.success(cursorResult);
    }

    @GetMapping("/{articleId}")
    ApiResponse<ArticleDetails> getArticleDetails(
            @PathVariable(value = "articleId") Long articleId,
            @AuthenticationPrincipal Long loginMemberId
    ) {
        var articleDetails = articleReadService.getArticleDetails(articleId, loginMemberId);
        return ApiResponse.success(articleDetails);
    }

    @GetMapping("/profiles")
    ApiResponse<CursorResult<ArticleActivity>> getArticleListByMember(
            @AuthenticationPrincipal Long loginMemberId,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor,
            @RequestParam(name = "tp", required = false, defaultValue = "SHARE") String type
    ) {
        var activities = articleReadService.getArticleListByMemberId(size, cursor, ArticleType.from(type), loginMemberId);
        var cursorResult = CursorResult.of(activities, size, ArticleActivity::getId);
        return ApiResponse.success(cursorResult);
    }
}

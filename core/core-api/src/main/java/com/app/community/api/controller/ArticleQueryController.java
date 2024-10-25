package com.app.community.api.controller;

import com.app.community.api.support.response.CursorResult;
import com.app.community.domain.query.ArticleQuery.ArticleActivity;
import com.app.community.domain.query.ArticleQuery.ArticleDetails;
import com.app.community.domain.query.ArticleQuery.ArticleSummary;
import com.app.community.domain.query.ArticleQueryService;
import com.app.community.domain.model.article.ArticleType;
import com.app.community.domain.model.member.LoginMember;
import com.app.community.api.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class ArticleQueryController {

    private final ArticleQueryService articleQueryService;

    @GetMapping()
    ApiResponse<CursorResult<ArticleSummary>> getArticleList(
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor,
            @RequestParam(name = "tp", required = false, defaultValue = "SHARE") String type,
            @RequestParam(name = "sort", required = false, defaultValue = "RECENT") String sort,
            @RequestParam(name = "authorId", required = false) Long authorId,
            @RequestParam(name = "tag", required = false) String tag
    ) {
        var articles = articleQueryService.getLatestArticleList(size + 1, cursor, ArticleType.from(type));
        var cursorResult = CursorResult.of(articles, size, ArticleSummary::getId);
        return ApiResponse.success(cursorResult);
    }

    @GetMapping("/{articleId}")
    ApiResponse<ArticleDetails> getArticleDetails(
            @PathVariable(value = "articleId") Long articleId,
            @AuthenticationPrincipal Long loginMemberId
    ) {
        var articleDetails = articleQueryService.getArticleDetails(articleId, loginMemberId);
        return ApiResponse.success(articleDetails);
    }

    @GetMapping("/profiles")
    ApiResponse<CursorResult<ArticleActivity>> getArticleListByMember(
            @AuthenticationPrincipal LoginMember member,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor,
            @RequestParam(name = "tp", required = false, defaultValue = "SHARE") String type
    ) {
        var activities = articleQueryService.getArticleListByMemberId(size + 1, cursor, ArticleType.from(type), member.memberId());
        var cursorResult = CursorResult.of(activities, size, ArticleActivity::getId);
        return ApiResponse.success(cursorResult);
    }
}

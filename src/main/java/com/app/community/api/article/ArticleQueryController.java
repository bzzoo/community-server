package com.app.community.api.article;

import com.app.community.domain.aritcle.Article;
import com.app.community.domain.aritcle.ArticleReadService;
import com.app.community.domain.aritcle.ArticleSummary;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class ArticleQueryController {

    private final ArticleReadService articleReadService;

    @GetMapping("")
    public ResponseEntity<CursorResult<ArticleSummary.ArticleInfo>> getArticleList(
            @RequestParam(name = "s", required = false, defaultValue = "20") int size,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor,
            @RequestParam(name = "t", required = false) String type
    ) {
        CursorResult<ArticleSummary.ArticleInfo> latestArticleList = articleReadService
                .getLatestArticleList(size, cursor, Article.ArticleType.fromString(type));
        return ResponseEntity.ok().body(latestArticleList);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleSummary.ArticleDetails> getArticleDetails(
            @PathVariable(value = "articleId") Long articleId,
            @AuthenticationPrincipal Long loginMemberId
    ) {
        ArticleSummary.ArticleDetails articleDetails = articleReadService.getArticleDetails(articleId, loginMemberId);
        return ResponseEntity.ok().body(articleDetails);
    }

    @GetMapping("/profiles/{memberId}")
    public ResponseEntity<CursorResult<ArticleSummary.ArticleActivity>> getArticleListByMember(
            @PathVariable(value = "memberId") Long memberId,
            @RequestParam(name = "s", required = false, defaultValue = "20") int size,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor,
            @RequestParam(name = "t", required = false, defaultValue = "SHARE") String type
    ) {
        CursorResult<ArticleSummary.ArticleActivity> articleList = articleReadService
                .getArticleListByMemberId(size, cursor, Article.ArticleType.fromString(type), memberId);
        return ResponseEntity.ok().body(articleList);
    }
}

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
                .getLatestArticleList(cursor, size, Article.ArticleType.fromString(type));
        return ResponseEntity.ok().body(latestArticleList);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleSummary.ArticleDetails> getTownDetails(
            @PathVariable(value = "articleId") Long articleId,
            @AuthenticationPrincipal Long loginMemberId
    ) {
        ArticleSummary.ArticleDetails articleDetails = articleReadService.getArticleDetails(articleId, loginMemberId);
        return ResponseEntity.ok().body(articleDetails);
    }
}

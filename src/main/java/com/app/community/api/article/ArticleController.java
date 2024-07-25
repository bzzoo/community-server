package com.app.community.api.article;

import com.app.community.domain.aritcle.Article;
import com.app.community.domain.aritcle.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/new")
    public ResponseEntity<Void> create(
            @AuthenticationPrincipal Long memberId,
            @RequestBody ArticleRequest.CreateRequest request
    ) {
        articleService.create(
                memberId,
                request.title(),
                request.content(),
                Article.ArticleType.valueOf(request.articleType()),
                request.keywordList()
        );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{articleId}")
    public ResponseEntity<Void> update(
            @AuthenticationPrincipal Long memberId,
            @PathVariable(name = "articleId") Long articleId,
            @RequestBody ArticleRequest.UpdateRequest request
    ) {
        articleService.update(
                memberId,
                articleId,
                request.title(),
                request.content(),
                request.keywordList()
        );
        return ResponseEntity.ok().build();
    }
}

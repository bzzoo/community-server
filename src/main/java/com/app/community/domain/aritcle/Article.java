package com.app.community.domain.aritcle;

import com.app.community.support.error.CoreApiException;
import com.app.community.support.error.ErrorType;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Article {

    private @Nullable Long id;
    private @NotNull Long writerId;
    private @NotNull String title;
    private @NotNull String content;
    private @NotNull ArticleStatus articleStatus;
    private @NotNull ArticleType articleType;
    private List<Keyword> keywordList;

    public enum ArticleStatus {
        TEMPORARY, STEADY, DELETED;
    }

    public enum ArticleType {
        SHARE, QUESTION;
    }

    @Builder
    private Article(
            @Nullable Long id,
            @NotNull Long writerId,
            @NotNull String title,
            @NotNull String content,
            @NotNull ArticleStatus articleStatus,
            @NotNull ArticleType articleType,
            List<Keyword> keywordList
    ) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.articleStatus = articleStatus;
        this.articleType = articleType;
        this.keywordList = keywordList;
    }

    public static Article create(
            @NotNull Long memberId,
            @NotNull String title,
            @NotNull String content,
            @NotNull ArticleType articleType,
            List<Keyword> keywordList
    ) {
        return Article.builder()
                .writerId(memberId)
                .title(title)
                .content(content)
                .articleType(articleType)
                .articleStatus(ArticleStatus.STEADY)
                .keywordList(keywordList)
                .build();
    }

    public void updateContent(String title, String content, List<Keyword> newKeywordList) {
        this.title = title;
        this.content = content;
        this.keywordList = newKeywordList;
    }

    public void validateOwner(Long memberId) {
        if (!this.writerId.equals(memberId)) throw new CoreApiException(ErrorType.DEFAULT_ERROR);
    }

    public void delete() {
        this.articleStatus = ArticleStatus.DELETED;
    }
}

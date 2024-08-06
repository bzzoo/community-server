package com.app.community.domain.aritcle;

import com.app.community.domain.member.MemberPointManager;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleReader articleReader;
    private final ArticleAppender articleAppender;
    private final KeywordAppender keywordAppender;
    private final MemberPointManager pointManager;
    private final ArticleUpdateValidate updateValidate;

    @Transactional
    public void create(
            @NotNull Long memberId,
            @NotNull String title,
            @NotNull String content,
            Article.@NotNull ArticleType articleType,
            List<String> keywordNameList
    ) {
        List<Keyword> keywordList = keywordAppender.saveIfNotExists(keywordNameList);
        Article article = articleAppender.append(memberId, title, content, articleType, keywordList);
        pointManager.addPointForPosting(memberId, article.getId(), articleType);
    }

    @Transactional
    public void update(
            @NotNull Long memberId,
            @NotNull Long articleId,
            @NotNull String title,
            @NotNull String content,
            List<String> newKeywordNameList
    ) {
        updateValidate.isUpdatable(articleId);
        var article = articleReader.getById(articleId);
        List<Keyword> keywordList = keywordAppender.saveIfNotExists(newKeywordNameList);
        articleAppender.update(memberId, article, title, content, keywordList);
    }

    @Transactional
    public void delete(
            @NotNull Long memberId,
            @NotNull Long articleId
    ) {
        updateValidate.isUpdatable(articleId);
        var article = articleReader.getById(articleId);
        pointManager.subtractPointForDeletion(article);
        articleAppender.delete(memberId, article);
    }
}

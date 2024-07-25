package com.app.community.storage.aritcle;

import com.app.community.domain.aritcle.Article;
import com.app.community.domain.aritcle.ArticleRepositoryForQuery;
import com.app.community.domain.aritcle.ArticleSummary;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.app.community.storage.aritcle.QArticleEntity.articleEntity;
import static com.app.community.storage.aritcle.QArticleKeywordEntity.articleKeywordEntity;
import static com.app.community.storage.member.QMemberEntity.memberEntity;

@RequiredArgsConstructor
@Repository
public class ArticleQueryRepository implements ArticleRepositoryForQuery {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ArticleSummary.ArticleInfo> findArticleList(int size, Long cursor, Article.ArticleType type) {
        List<Tuple> results = queryFactory.select(
                        articleEntity.id,
                        articleEntity.title,
                        articleEntity.content,
                        articleEntity.articleType,
                        memberEntity.id,
                        memberEntity.nickname,
                        memberEntity.profileImagePath,
                        memberEntity.createdAt,
                        memberEntity.updatedAt,
                        articleEntity.createdAt,
                        articleEntity.updatedAt,
                        articleKeywordEntity.keywordId,
                        articleKeywordEntity.keywordName
                )
                .from(articleEntity)
                .leftJoin(memberEntity).on(articleEntity.writerId.eq(memberEntity.id))
                .leftJoin(articleKeywordEntity).on(articleKeywordEntity.articleId.eq(articleEntity.id))
                .where(articleEntity.articleStatus.eq(Article.ArticleStatus.STEADY)
                        .and(cursor == -1 ? null : articleEntity.id.lt(cursor))
                        .and(type == null ? null : articleEntity.articleType.eq(type)))
                .orderBy(articleEntity.id.desc())
                .limit(size + 1)
                .fetch();

        Map<Long, ArticleSummary.ArticleInfo> articleInfoMap = results.stream().collect(
                Collectors.toMap(
                        tuple -> tuple.get(articleEntity.id),
                        tuple -> new ArticleSummary.ArticleInfo(
                                tuple.get(articleEntity.id),
                                tuple.get(articleEntity.title),
                                tuple.get(articleEntity.content),
                                tuple.get(articleEntity.articleType),
                                new ArticleSummary.Author(
                                        tuple.get(memberEntity.id),
                                        tuple.get(memberEntity.nickname),
                                        tuple.get(memberEntity.profileImagePath),
                                        tuple.get(memberEntity.createdAt),
                                        tuple.get(memberEntity.updatedAt)
                                ),
                                tuple.get(articleEntity.createdAt),
                                tuple.get(articleEntity.updatedAt),
                                new ArrayList<>()
                        ),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new // Use LinkedHashMap to maintain order
                )
        );

        results.forEach(tuple -> {
            ArticleSummary.ArticleInfo articleInfo = articleInfoMap.get(tuple.get(articleEntity.id));
            if (tuple.get(articleKeywordEntity.keywordId) != null) {
                articleInfo.keywordList().add(new ArticleSummary.ArticleKeywordInfo(
                        tuple.get(articleKeywordEntity.keywordId),
                        tuple.get(articleKeywordEntity.keywordName)
                ));
            }
        });
        return new ArrayList<>(articleInfoMap.values());
    }

    @Override
    public ArticleSummary.ArticleDetails findArticleDetails(Long articleId, Long memberId) {
        List<Tuple> results = queryFactory.select(
                        articleEntity.id,
                        articleEntity.title,
                        articleEntity.content,
                        articleEntity.articleType,
                        memberEntity.id,
                        memberEntity.nickname,
                        memberEntity.profileImagePath,
                        memberEntity.createdAt,
                        memberEntity.updatedAt,
                        articleEntity.createdAt,
                        articleEntity.updatedAt,
                        articleKeywordEntity.keywordId,
                        articleKeywordEntity.keywordName
                )
                .from(articleEntity)
                .leftJoin(memberEntity).on(articleEntity.writerId.eq(memberEntity.id))
                .leftJoin(articleKeywordEntity).on(articleKeywordEntity.articleId.eq(articleEntity.id))
                .where(articleEntity.articleStatus.eq(Article.ArticleStatus.STEADY).and(articleEntity.id.eq(articleId)))
                .fetch();

        if (results.isEmpty()) {
            return null;
        }

        Tuple firstTuple = results.get(0);
        ArticleSummary.Author author = new ArticleSummary.Author(
                firstTuple.get(memberEntity.id),
                firstTuple.get(memberEntity.nickname),
                firstTuple.get(memberEntity.profileImagePath),
                firstTuple.get(memberEntity.createdAt),
                firstTuple.get(memberEntity.updatedAt)
        );

        List<ArticleSummary.ArticleKeywordInfo> keywordList = results.stream()
                .filter(tuple -> tuple.get(articleKeywordEntity.keywordId) != null)
                .map(tuple -> new ArticleSummary.ArticleKeywordInfo(
                        tuple.get(articleKeywordEntity.keywordId),
                        tuple.get(articleKeywordEntity.keywordName)
                ))
                .collect(Collectors.toList());

        return new ArticleSummary.ArticleDetails(
                firstTuple.get(articleEntity.id),
                firstTuple.get(articleEntity.title),
                firstTuple.get(articleEntity.content),
                firstTuple.get(articleEntity.articleType),
                author,
                firstTuple.get(articleEntity.createdAt),
                firstTuple.get(articleEntity.updatedAt),
                keywordList
        );

    }
}
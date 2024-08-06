package com.app.community.storage.comment;

import com.app.community.domain.comment.CommentRepositoryForQuery;
import com.app.community.domain.comment.CommentSummary;
import com.app.community.storage.member.QMemberEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.app.community.storage.comment.QCommentEntity.commentEntity;
import static com.app.community.storage.aritcle.QArticleEntity.articleEntity;

@RequiredArgsConstructor
@Repository
public class CommentQueryRepository implements CommentRepositoryForQuery {

    private final JPAQueryFactory queryFactory;

    public List<CommentSummary.CommentInfo> findCommentListByArticleId(Long articleId, Long cursor) {
        QCommentEntity commentEntity = QCommentEntity.commentEntity;
        QCommentEntity childCommentEntity = new QCommentEntity("childCommentEntity");
        QMemberEntity memberEntity = QMemberEntity.memberEntity;
        QMemberEntity childMemberEntity = new QMemberEntity("childMemberEntity");

        BooleanBuilder where = new BooleanBuilder();

        where.and(commentEntity.articleId.eq(articleId).and(commentEntity.parentCommentId.isNull()));
        if (cursor != -1) {
            where.and(commentEntity.id.lt(cursor));
        }

        List<Tuple> result = queryFactory.select(
                        commentEntity.id,
                        commentEntity.articleId,
                        commentEntity.parentCommentId,
                        commentEntity.content,
                        commentEntity.isDelete,
                        memberEntity.id,
                        memberEntity.nickname,
                        memberEntity.profileImagePath,
                        memberEntity.createdAt,
                        memberEntity.updatedAt,
                        commentEntity.createdAt,
                        commentEntity.updatedAt,
                        childCommentEntity.id,
                        childCommentEntity.content,
                        childCommentEntity.createdAt,
                        childCommentEntity.updatedAt,
                        childCommentEntity.isDelete,
                        childMemberEntity.id,
                        childMemberEntity.nickname,
                        childMemberEntity.profileImagePath,
                        childMemberEntity.createdAt,
                        childMemberEntity.updatedAt
                )
                .from(commentEntity)
                .leftJoin(memberEntity).on(commentEntity.writerId.eq(memberEntity.id))
                .leftJoin(childCommentEntity).on(childCommentEntity.parentCommentId.eq(commentEntity.id))
                .leftJoin(childMemberEntity).on(childCommentEntity.writerId.eq(childMemberEntity.id))
                .where(where)
                .orderBy(commentEntity.createdAt.asc())
                .limit(41)
                .fetch();

        Map<Long, CommentSummary.CommentInfo> commentMap = new HashMap<>();
        List<CommentSummary.CommentInfo> topLevelComments = new ArrayList<>();

        for (Tuple tuple : result) {
            Long commentId = tuple.get(commentEntity.id);
            Long parentId = tuple.get(commentEntity.parentCommentId);

            CommentSummary.Author author = new CommentSummary.Author(
                    tuple.get(memberEntity.id),
                    tuple.get(memberEntity.nickname),
                    tuple.get(memberEntity.profileImagePath),
                    tuple.get(memberEntity.createdAt),
                    tuple.get(memberEntity.updatedAt)
            );

            CommentSummary.CommentInfo commentInfo = commentMap.get(commentId);
            if (commentInfo == null) {
                commentInfo = new CommentSummary.CommentInfo(
                        commentId,
                        tuple.get(commentEntity.articleId),
                        parentId,
                        tuple.get(commentEntity.content),
                        tuple.get(commentEntity.isDelete),
                        author,
                        new ArrayList<>(),
                        tuple.get(commentEntity.createdAt),
                        tuple.get(commentEntity.updatedAt)
                );
                commentMap.put(commentId, commentInfo);
                if (parentId == null) {
                    topLevelComments.add(commentInfo);
                } else {
                    CommentSummary.CommentInfo parentComment = commentMap.get(parentId);
                    if (parentComment != null) {
                        parentComment.childCommentList().add(commentInfo);
                    }
                }
            }

            Long childCommentId = tuple.get(childCommentEntity.id);
            if (childCommentId != null) {
                CommentSummary.Author childAuthor = new CommentSummary.Author(
                        tuple.get(childMemberEntity.id),
                        tuple.get(childMemberEntity.nickname),
                        tuple.get(childMemberEntity.profileImagePath),
                        tuple.get(childMemberEntity.createdAt),
                        tuple.get(childMemberEntity.updatedAt)
                );

                CommentSummary.CommentInfo childCommentInfo = new CommentSummary.CommentInfo(
                        childCommentId,
                        tuple.get(commentEntity.articleId),
                        commentId,
                        tuple.get(childCommentEntity.content),
                        tuple.get(childCommentEntity.isDelete),
                        childAuthor,
                        new ArrayList<>(),
                        tuple.get(childCommentEntity.createdAt),
                        tuple.get(childCommentEntity.updatedAt)
                );
                commentInfo.childCommentList().add(childCommentInfo);
                commentMap.put(childCommentId, childCommentInfo);
            }
        }

        return topLevelComments;
    }

    @Override
    public List<CommentSummary.ProfileComment> findAnswerByMember(Long memberId, Long cursor) {
        List<Tuple> fetch = queryFactory.select(
                commentEntity.id,
                commentEntity.articleId,
                articleEntity.title,
                commentEntity.content,
                commentEntity.isDelete,
                commentEntity.createdAt,
                commentEntity.updatedAt
                )
                .from(commentEntity)
                .leftJoin(articleEntity).on(articleEntity.id.eq(commentEntity.articleId))
                .where(commentEntity.writerId.eq(memberId)
                        .and(cursor == -1 ? null : commentEntity.id.lt(cursor))
                        .and(commentEntity.parentCommentId.isNull()))
                .orderBy(commentEntity.id.desc())
                .limit(20)
                .fetch();

       return fetch.stream().map(tuple -> new CommentSummary.ProfileComment(
                tuple.get(commentEntity.id),
                tuple.get(commentEntity.articleId),
                tuple.get(articleEntity.title),
                tuple.get(commentEntity.content),
                tuple.get(commentEntity.isDelete),
                tuple.get(commentEntity.createdAt),
                tuple.get(commentEntity.updatedAt)
        ))
                .collect(Collectors.toList());
    }
}



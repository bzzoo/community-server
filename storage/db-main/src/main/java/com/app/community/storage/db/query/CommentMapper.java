package com.app.community.storage.db.query;

import com.app.community.domain.query.CommentQuery.CommentDetails;
import com.app.community.domain.query.CommentQuery.ProfileComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentDetails> findCommentListByArticleId(
            @Param("articleId") Long articleId,
            @Param("size") int size,
            @Param("cursor") Long cursor
    );

    List<ProfileComment> findAnswerByMember(
            @Param("memberId") Long memberId,
            @Param("size") int size,
            @Param("cursor") Long cursor
    );

    List<CommentDetails> findMoreComments(
            @Param("articleId") Long articleId,
            @Param("parentId") Long parentId,
            @Param("size") int size,
            @Param("cursor") Long cursor,
            @Param("depth") int depth
    );

    CommentDetails findById(
            @Param("commentId") Long commentId
    );
}
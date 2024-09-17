package com.app.community.storage.db.query;

import com.app.community.domain.agg.comment.CommentQuery.CommentInfo;
import com.app.community.domain.agg.comment.CommentQuery.ProfileComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentInfo> findCommentListByArticleId(
            @Param("articleId") Long articleId,
            @Param("size") int size,
            @Param("cursor") Long cursor
    );

    List<ProfileComment> findAnswerByMember(
            @Param("memberId") Long memberId,
            @Param("size") int size,
            @Param("cursor") Long cursor
    );
}
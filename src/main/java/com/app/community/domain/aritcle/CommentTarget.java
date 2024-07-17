package com.app.community.domain.aritcle;

public record CommentTarget(
        Long targetId,
        TargetType targetType
) {

    public enum TargetType {
        ARTICLE, COMMENT
    }

}

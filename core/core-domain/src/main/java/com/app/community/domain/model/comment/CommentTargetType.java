package com.app.community.domain.model.comment;

import com.app.community.domain.support.error.DomainErrorType;
import com.app.community.domain.support.error.DomainException;

import java.util.Arrays;

public enum CommentTargetType {
    SHARE,
    COMMENT,
    QUESTION;

    public static CommentTargetType from(String type) {
        return Arrays.stream(CommentTargetType.values())
                .filter(targetType -> targetType.name().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new DomainException(DomainErrorType.INVALID_COMMENT_TARGET_TYPE));
    }
}
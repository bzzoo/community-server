package com.app.community.domain.support.error;

import lombok.Getter;
import org.slf4j.event.Level;

@Getter
public enum DomainErrorType {

    //서버 에러
    DEFAULT_ERROR("서버 에러", Level.ERROR),

    // 채팅 도메인 에러
    CHAT_OPPONENT_REFUSE("상대방이 새로운 채팅을 거부한 상태입니다.", Level.WARN),
    CHAT_ENDED("이미 종료된 채팅입니다.", Level.WARN),
    CHAT_SELF_REQUEST("본인에게 채팅을 요청할 수 없습니다.", Level.WARN),

    // 아티클 도메인 에러
    ARTICLE_UNMODIFIABLE("답변이 존재하는 질문 게시글은 수정할 수 없습니다.", Level.WARN),
    INVALID_ARTICLE_TYPE("존재 하지 않는 아티클 타입입니다.", Level.WARN),

    //댓글 도메인 에러
    INVALID_COMMENT_TARGET_TYPE("올바르지 않은 댓글 유형입니다.", Level.WARN)
    ;

    private final String message;
    private final Level logLevel;

    DomainErrorType(String message, Level logLevel) {
        this.message = message;
        this.logLevel = logLevel;
    }
}

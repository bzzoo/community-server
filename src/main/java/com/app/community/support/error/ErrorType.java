package com.app.community.support.error;

import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,  "An unexpected error has occurred.", LogLevel.ERROR),
    CHAT_OPPONENT_REFUSE(HttpStatus.INTERNAL_SERVER_ERROR,  "상대방이 채팅을 할 수 없는 상태입니다.", LogLevel.DEBUG),
    CHAT_ENDED(HttpStatus.INTERNAL_SERVER_ERROR,  "종료된 채팅입니다.", LogLevel.DEBUG),
    CHAT_SELF_REQUEST(HttpStatus.INTERNAL_SERVER_ERROR,  "본인에게 요청할 수 없습니다.", LogLevel.DEBUG),

    ARTICLE_UNMODIFIABLE(HttpStatus.BAD_REQUEST, "댓글이 존재하는 질문 게시글은 변경할 수 없습니다.", LogLevel.DEBUG)
    ;
    private final HttpStatus status;
    private final String message;
    private final LogLevel logLevel;

    ErrorType(HttpStatus status,String message, LogLevel logLevel) {
        this.status = status;
        this.message = message;
        this.logLevel = logLevel;
    }
}

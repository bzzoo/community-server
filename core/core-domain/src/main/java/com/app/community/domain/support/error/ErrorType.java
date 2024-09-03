package com.app.community.domain.support.error;

import lombok.Getter;


@Getter
public enum ErrorType {

    DEFAULT_ERROR(  "An unexpected error has occurred."),
    CHAT_OPPONENT_REFUSE("상대방이 채팅을 할 수 없는 상태입니다."),
    CHAT_ENDED( "종료된 채팅입니다."),
    CHAT_SELF_REQUEST(  "본인에게 요청할 수 없습니다."),

    ARTICLE_UNMODIFIABLE("댓글이 존재하는 질문 게시글은 변경할 수 없습니다.")
    ;
    private final String message;

    ErrorType(String message) {
        this.message = message;
    }
}

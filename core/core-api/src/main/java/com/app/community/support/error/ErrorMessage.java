package com.app.community.support.error;

import com.app.community.domain.support.error.DomainErrorType;
import lombok.Getter;

@Getter
public class ErrorMessage {

    private final String message;
    private final Object data;

    public ErrorMessage(DomainErrorType domainErrorType) {
        this.message = domainErrorType.getMessage();
        this.data = null;
    }

    public ErrorMessage(DomainErrorType domainErrorType, Object data) {
        this.message = domainErrorType.getMessage();
        this.data = data;
    }
}

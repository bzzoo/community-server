package com.app.community.domain.support.error;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    private final DomainErrorType domainErrorType;
    private final Object data;

    public DomainException(DomainErrorType domainErrorType) {
        super(domainErrorType.getMessage());
        this.domainErrorType = domainErrorType;
        this.data = null;
    }
}

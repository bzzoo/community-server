package com.app.community.storage.db.support.error;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("찾을 수 없습니다.");
    }
}

package com.app.community.domain.model.point;

public record PointAccount(
        Long memberId,
        Integer balance
) {
    public static PointAccount fromAccountNoting() {
        return new PointAccount(null, null);
    }

    public static PointAccount fromAccount(Long memberId, Integer balance) {
        return new PointAccount(memberId, balance);
    }

    public static PointAccount toAccount(Long memberId, Integer balance) {
        return new PointAccount(memberId, balance);
    }
}

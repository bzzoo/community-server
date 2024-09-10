package com.app.community.domain.agg.point;

public record PointAccount(
        Long memberId,
        Integer balance
) {
    public static PointAccount bySystem(){
        return new PointAccount(null, null);
    }
}

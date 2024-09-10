package com.app.community.domain.agg.point;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PointHistory {
    private Long id;
    private PointAccount fromAccount;
    private PointAccount toAccount;
    private PointReference reference;
    private PointEventType eventType;
    private Integer amount;


    public PointHistory(
            Long id,
            PointAccount fromAccount,
            PointAccount toAccount,
            PointReference reference,
            PointEventType eventType,
            Integer amount
    ) {
        this.id = id;
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.reference = reference;
        this.eventType = eventType;
        this.amount = amount;
    }

    public static PointHistory create(
            PointAccount fromAccount,
            PointAccount toAccount,
            PointReference reference,
            PointEventType eventType,
            Integer amount
    ) {
        return new PointHistory(null, fromAccount, toAccount, reference, eventType, amount);
    }
}

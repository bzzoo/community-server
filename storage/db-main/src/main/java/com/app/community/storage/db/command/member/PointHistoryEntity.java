package com.app.community.storage.db.command.member;

import com.app.community.domain.agg.point.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point_histories")
@Entity
public class PointHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fromMemberId;
    private Long toMemberId;
    private Integer fromMemberBalance;
    private Integer toMemberBalance;
    private int amount;
    private Long referenceId;
    @Enumerated(EnumType.STRING)
    private PointReferenceType pointReferenceType;
    @Enumerated(EnumType.STRING)
    private PointEventType eventType;

    public static PointHistoryEntity fromDomain(PointHistory pointHistory) {
        return PointHistoryEntity.builder()
                .fromMemberId(pointHistory.getFromAccount().memberId())
                .fromMemberBalance(pointHistory.getFromAccount().balance())
                .toMemberId(pointHistory.getToAccount().memberId())
                .toMemberBalance(pointHistory.getToAccount().balance())
                .amount(pointHistory.getAmount())
                .referenceId(pointHistory.getReference().referenceId())
                .pointReferenceType(pointHistory.getReference().pointReferenceType())
                .eventType(pointHistory.getEventType())
                .build();
    }

    public  PointHistory toDomain(){
        PointAccount toAccount = new PointAccount(fromMemberId, fromMemberBalance);
        PointAccount fromAccount = new PointAccount(fromMemberId, fromMemberBalance);
        PointReference reference = new PointReference(referenceId, pointReferenceType);
        return new PointHistory(
                id,
                toAccount,
                fromAccount,
                reference,
                eventType,
                amount
        );
    }
}
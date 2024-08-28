package com.app.community.storage.db.command.member;

import com.app.community.domain.agg.point.PointEventType;
import com.app.community.domain.agg.point.PointHistory;
import com.app.community.domain.agg.point.ReferenceType;
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
    private long fromMemberBalance;
    private long toMemberBalance;
    private int amount;
    private Long referenceId;
    @Enumerated(EnumType.STRING)
    private ReferenceType referenceType;
    @Enumerated(EnumType.STRING)
    private PointEventType eventType;

    public static PointHistoryEntity fromDomain(PointHistory pointHistory) {
        return PointHistoryEntity.builder()
                .fromMemberId(pointHistory.getFromMemberId())
                .toMemberId(pointHistory.getToMemberId())
                .amount(pointHistory.getAmount())
                .referenceId(pointHistory.getReferenceId())
                .referenceType(pointHistory.getReferenceType())
                .eventType(pointHistory.getEventType())
                .build();
    }

    public PointHistory toDomain() {
        return PointHistory.builder()
                .fromMemberId(fromMemberId)
                .toMemberId(toMemberId)
                .amount(amount)
                .referenceId(referenceId)
                .referenceType(referenceType)
                .eventType(eventType)
                .build();
    }
}
package com.app.community.storage.member;

import com.app.community.domain.member.PointHistory;
import jakarta.persistence.*;
import lombok.*;

@Setter
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
    private Long memberId;
    private Integer value;
    private Long targetId;
    @Enumerated(EnumType.STRING)
    private PointHistory.PointTransactionType type;

    public static PointHistoryEntity fromDomain(PointHistory pointHistory){
        return PointHistoryEntity.builder()
                .memberId(pointHistory.getMemberId())
                .value(pointHistory.getValue())
                .targetId(pointHistory.getTargetId())
                .type(pointHistory.getType())
                .build();
    }

    public PointHistory toDomain(){
        return PointHistory.builder()
                .memberId(memberId)
                .value(value)
                .targetId(targetId)
                .type(type)
                .build();
    }
}

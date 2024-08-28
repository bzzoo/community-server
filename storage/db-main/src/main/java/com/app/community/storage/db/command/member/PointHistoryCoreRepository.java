package com.app.community.storage.db.command.member;

import com.app.community.domain.agg.point.PointHistory;
import com.app.community.domain.agg.point.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class PointHistoryCoreRepository implements PointHistoryRepository {

    private final PointHistoryJpaRepository pointHistoryJpaRepository;

    @Override
    public PointHistory save(PointHistory pointHistory) {
        return pointHistoryJpaRepository.save(PointHistoryEntity.fromDomain(pointHistory)).toDomain();
    }

    @Override
    public List<PointHistory> findAllTransactionsByMemberId(Long memberId, Long targetId) {
        List<PointHistoryEntity> historyList = pointHistoryJpaRepository.findAllTransactionsByMemberId(memberId);
        return historyList.stream().map(PointHistoryEntity::toDomain).collect(Collectors.toList());
    }
}

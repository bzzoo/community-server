package com.app.community.storage.db.command.member;

import com.app.community.domain.model.point.PointHistory;
import com.app.community.domain.model.point.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PointHistoryCoreRepository implements PointHistoryRepository {

    private final PointHistoryJpaRepository pointHistoryJpaRepository;

    @Override
    public PointHistory save(PointHistory pointHistory) {
        return pointHistoryJpaRepository.save(PointHistoryEntity.fromDomain(pointHistory)).toDomain();
    }
}

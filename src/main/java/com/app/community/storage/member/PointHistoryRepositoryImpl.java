package com.app.community.storage.member;

import com.app.community.domain.member.PointHistory;
import com.app.community.domain.member.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class PointHistoryRepositoryImpl implements PointHistoryRepository {

    private final PointHistoryJpaRepository pointHistoryJpaRepository;

    @Override
    public PointHistory save(PointHistory pointHistory) {
        return pointHistoryJpaRepository.save(PointHistoryEntity.fromDomain(pointHistory)).toDomain();
    }

    @Override
    public List<PointHistory> findByMemberIdAndTargetId(Long memberId, Long targetId) {
        List<PointHistoryEntity> historyList = pointHistoryJpaRepository.findByMemberIdAndTargetId(memberId, targetId);
        return historyList.stream().map(PointHistoryEntity::toDomain).collect(Collectors.toList());
    }
}

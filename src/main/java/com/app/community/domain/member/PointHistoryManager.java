package com.app.community.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class PointHistoryManager {

    private final PointHistoryRepository historyRepository;

    @Transactional
    public void createPointHistory(Long memberId, int points, Long targetId, PointHistory.PointTransactionType transactionType) {
        PointHistory pointHistory = PointHistory.create(memberId, points, targetId, transactionType);
        historyRepository.save(pointHistory);
    }

    public int calculatePointsToSubtract(Long memberId, Long targetId) {
        return historyRepository.findByMemberIdAndTargetId(memberId, targetId).stream()
                .mapToInt(PointHistory::getValue)
                .sum();
    }
}

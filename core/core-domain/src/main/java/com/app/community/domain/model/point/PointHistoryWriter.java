package com.app.community.domain.model.point;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointHistoryWriter {

    private final PointHistoryRepository historyRepository;

    public void create(
            PointAccount fromAccount,
            PointAccount toAccount,
            PointReference reference,
            PointEventType pointEventType,
            Integer amount
    ) {
        PointHistory pointHistory = PointHistory.create(fromAccount, toAccount, reference, pointEventType, amount);
        historyRepository.save(pointHistory);
    }
}

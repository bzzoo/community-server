package com.app.community.domain.member;

import java.util.List;

public interface PointHistoryRepository {

    PointHistory save(PointHistory pointHistory);

    List<PointHistory> findByMemberIdAndTargetId(Long memberId, Long targetId);
}

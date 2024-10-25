package com.app.community.domain.query;

import com.app.community.domain.model.point.PointHistory;

import java.util.List;

public interface MemberQueryRepository {
    MemberQuery.MemberInfo getById(Long memberId);
    List<PointHistory> getPointHistory(Long memberId, int size, Long cursor);
}

package com.app.community.domain.agg.member;

import com.app.community.domain.agg.point.PointHistory;

import java.util.List;

public interface MemberRepositoryForQuery {
    MemberQuery.MemberInfo getById(Long memberId);
    List<PointHistory> getPointHistory(Long memberId, int size, Long cursor);
}

package com.app.community.domain.member;

public interface MemberRepositoryForQuery {
    MemberSummary.MemberInfo getById(Long memberId);
}

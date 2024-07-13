package com.app.community.storage.member;

import com.app.community.domain.member.MemberRepositoryForQuery;
import com.app.community.domain.member.MemberSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository implements MemberRepositoryForQuery {

    @Override
    public MemberSummary.MemberInfo getById(Long memberId) {
        return null;
    }
}

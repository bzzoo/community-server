package com.app.community.domain.member;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberReadService {

    private final MemberRepositoryForQuery memberRepositoryForQuery;

    public MemberSummary.MemberInfo getMemberInfo(@NotNull Long memberId){
        return memberRepositoryForQuery.getById(memberId);
    }
}

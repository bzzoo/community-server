package com.app.community.storage.member;

import com.app.community.domain.member.Member;
import com.app.community.domain.member.MemberRepositoryForQuery;
import com.app.community.domain.member.MemberSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository implements MemberRepositoryForQuery {

    private final MemberJPARepository memberJPARepository;

    @Override
    public MemberSummary.MemberInfo getById(Long memberId) {
        Member member = memberJPARepository.findById(memberId).map(MemberEntity::toDomain).orElseThrow();
        return MemberSummary.MemberInfo.of(member);
    }
}

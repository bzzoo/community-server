package com.app.community.storage.db.command.member;

import com.app.community.domain.agg.member.Member;
import com.app.community.domain.agg.member.MemberQuery.MemberInfo;
import com.app.community.domain.agg.member.MemberRepositoryForQuery;
import com.app.community.domain.agg.point.PointHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository implements MemberRepositoryForQuery {

    private final MemberJpaRepository memberJPARepository;
    private final PointHistoryJpaRepository historyJpaRepository;

    @Override
    public MemberInfo getById(Long memberId) {
        Member member = memberJPARepository.findById(memberId).map(MemberEntity::toDomain).orElseThrow();
        return MemberInfo.of(member);
    }

    public List<PointHistory> getPointHistory(Long memberId){
        return historyJpaRepository.findAllTransactionsByMemberId(memberId).stream()
                .map(PointHistoryEntity::toDomain)
                .collect(Collectors.toList());
    }
}

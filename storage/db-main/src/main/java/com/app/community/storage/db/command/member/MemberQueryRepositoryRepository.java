package com.app.community.storage.db.command.member;

import com.app.community.domain.model.member.Member;
import com.app.community.domain.query.MemberQuery.MemberInfo;
import com.app.community.domain.model.point.PointHistory;
import com.app.community.storage.db.support.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepositoryRepository implements com.app.community.domain.query.MemberQueryRepository {

    private final MemberJpaRepository memberJPARepository;
    private final PointHistoryJpaRepository historyJpaRepository;

    @Override
    public MemberInfo getById(Long memberId) {
        Member member = memberJPARepository.findById(memberId)
                .map(MemberEntity::toDomain)
                .orElseThrow(NotFoundException::new);
        return MemberInfo.of(member);
    }

    public List<PointHistory> getPointHistory(Long memberId, int size, Long cursor) {
        return historyJpaRepository.findAllTransactionsByMemberId(memberId, cursor, Pageable.ofSize(size)).stream()
                .map(PointHistoryEntity::toDomain)
                .collect(Collectors.toList());
    }
}

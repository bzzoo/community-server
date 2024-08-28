package com.app.community.domain.agg.member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findBySocialInfo(MemberSocial socialInfo);
}

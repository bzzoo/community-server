package com.app.community.domain.member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findBySocialInfo(Member.SocialInfo socialInfo);
    Optional<Member> findById(Long id);
}

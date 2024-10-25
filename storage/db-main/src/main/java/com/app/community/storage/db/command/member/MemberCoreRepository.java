package com.app.community.storage.db.command.member;

import com.app.community.domain.model.member.Member;
import com.app.community.domain.model.member.MemberRepository;
import com.app.community.domain.model.member.MemberSocial;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberCoreRepository implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(MemberEntity.fromDomain(member)).toDomain();
    }


    @Override
    public Optional<Member> findBySocialInfo(MemberSocial socialInfo) {
        return memberJpaRepository.findBySocial(
                socialInfo.socialId(),
                socialInfo.memberSocialType()
        ).map(MemberEntity::toDomain);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberJpaRepository.findById(memberId)
                .map(MemberEntity::toDomain);
    }
}

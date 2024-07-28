package com.app.community.storage.member;

import com.app.community.domain.member.Member;
import com.app.community.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJPARepository memberJPARepository;

    @Override
    public Optional<Member> findBySocialInfo(Member.SocialInfo socialInfo) {
        return memberJPARepository.findBySocial(
                        socialInfo.socialId(),
                        socialInfo.memberSocialType()
                )
                .map(MemberEntity::toDomain);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberJPARepository.findById(memberId)
                .map(MemberEntity::toDomain);
    }

    @Override
    public Member save(Member member) {
        return memberJPARepository.save(MemberEntity.fromDomain(member)).toDomain();
    }
}

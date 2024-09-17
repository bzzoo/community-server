package com.app.community.storage.db.command.member;

import com.app.community.domain.agg.member.Member;
import com.app.community.domain.agg.member.MemberRepository;
import com.app.community.domain.agg.member.MemberSocial;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberCoreRepository implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member member) {
        MemberEntity memberEntity = new MemberEntity(
                member.getId(),
                member.getProfile().email(),
                member.getProfile().nickname().value(),
                member.getProfile().profileImagePath(),
                member.getProfile().memberSetting().chatPeePoint(),
                member.getProfile().memberSetting().chatRefusal(),
                member.getProfile().position(),
                member.getGrade().value(),
                member.getGrade().tier(),
                member.getSocialInfo().socialId(),
                member.getSocialInfo().memberSocialType(),
                member.getStatus()
        );
        return memberJpaRepository.save(memberEntity).toDomain();
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

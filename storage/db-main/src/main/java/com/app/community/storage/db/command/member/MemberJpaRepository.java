package com.app.community.storage.db.command.member;

import com.app.community.domain.agg.member.MemberSocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

    @Query("""
            SELECT m FROM MemberEntity m
            WHERE (m.memberSocialType = :memberSocialType)
            AND (m.socialId = :socialId)
            """
    )
    Optional<MemberEntity> findBySocial(String socialId, MemberSocialType memberSocialType);
}

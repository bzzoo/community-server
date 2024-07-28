package com.app.community.storage.member;

import com.app.community.domain.member.MemberSocialType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberJPARepository extends JpaRepository<MemberEntity, Long> {

    @Query("""
            SELECT m FROM MemberEntity m
            WHERE (m.memberSocialType = :memberSocialType)
            AND (m.socialId = :socialId)
            """
    )
    Optional<MemberEntity> findBySocial(@NotNull String socialId, @NotNull MemberSocialType memberSocialType);
}

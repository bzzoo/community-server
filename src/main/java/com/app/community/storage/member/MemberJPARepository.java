package com.app.community.storage.member;

import com.app.community.domain.member.SocialType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberJPARepository extends JpaRepository<MemberEntity, Long> {

    @Query("""
            SELECT m FROM MemberEntity m
            WHERE (m.socialType = :socialType)
            AND (m.socialId = :socialId)
            """
    )
    Optional<MemberEntity> findBySocial(@NotNull String socialId, @NotNull SocialType socialType);
}

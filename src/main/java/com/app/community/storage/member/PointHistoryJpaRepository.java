package com.app.community.storage.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistoryEntity, Long> {
    List<PointHistoryEntity> findByMemberIdAndTargetId(Long memberId, Long targetId);
}

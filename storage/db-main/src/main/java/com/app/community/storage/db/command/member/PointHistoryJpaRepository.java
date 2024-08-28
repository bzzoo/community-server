package com.app.community.storage.db.command.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistoryEntity, Long> {
    @Query("SELECT ph FROM PointHistoryEntity ph WHERE ph.toMemberId = :memberId OR ph.fromMemberId = :memberId")
    List<PointHistoryEntity> findAllTransactionsByMemberId(@Param("memberId") Long memberId);
}

package com.app.community.domain.agg.member;

import com.app.community.domain.agg.point.PointHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberReadService {

    private final MemberRepositoryForQuery memberRepositoryForQuery;

    public MemberQuery.MemberInfo getMemberInfo(Long memberId){
        return memberRepositoryForQuery.getById(memberId);
    }

    public List<PointHistory> getPointHistory(Long memberId, int size){
        return memberRepositoryForQuery.getPointHistory(memberId);
    }
}

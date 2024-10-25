package com.app.community.domain.query;

import com.app.community.domain.model.point.PointHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberQueryService {

    private final MemberQueryRepository memberQueryRepository;

    public MemberQuery.MemberInfo getMemberInfo(Long memberId){
        return memberQueryRepository.getById(memberId);
    }

    public List<PointHistory> getPointHistory(Long memberId, int size, Long cursor){
        return memberQueryRepository.getPointHistory(memberId , size, cursor);
    }
}

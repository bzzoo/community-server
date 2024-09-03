package com.app.community.controller.request;

import com.app.community.domain.agg.member.MemberNickname;
import com.app.community.domain.agg.member.MemberPosition;
import com.app.community.domain.agg.member.MemberProfile;
import com.app.community.domain.agg.member.MemberSetting;

public class MemberRequests {

    public record UpdateProfileRequest(
            String email,
            String profileImagePath,
            String nickname,
            int chatPeePoint,
            boolean chatRefusal,
            MemberPosition memberPosition
    ){
        public MemberProfile toProfile(){
            MemberSetting memberSetting = new MemberSetting(chatPeePoint, chatRefusal);
            MemberNickname memberNickname = new MemberNickname(nickname);
            return new MemberProfile(
                    email,
                    profileImagePath,
                    memberNickname,
                    memberSetting,
                    memberPosition
            );
        }
    }
}

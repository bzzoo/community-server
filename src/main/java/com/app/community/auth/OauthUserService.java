package com.app.community.auth;

import com.app.community.domain.member.Member;
import com.app.community.domain.member.MemberAppender;
import com.app.community.domain.member.MemberReader;
import com.app.community.domain.member.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OauthUserService extends DefaultOAuth2UserService {

    private final MemberReader memberReader;
    private final MemberAppender memberAppender;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OauthResponse response = decideProvider(userRequest, oAuth2User);
        Member.SocialInfo socialInfo = new Member.SocialInfo(
                response.getUserId(),
                SocialType.from(response.getProviderName())
        );
        Member member = getOrRegisterMember(socialInfo, response);
        return new OauthUser(new OauthPrincipal(member.getId()));
    }

    private Member getOrRegisterMember(Member.SocialInfo socialInfo, OauthResponse response) {
       return memberReader.findBySocial(socialInfo)
                .orElseGet(() -> memberAppender.register(
                        socialInfo,
                        response.getEmail(),
                        response.getProfileImagePath()
                ));
    }

    private OauthResponse decideProvider(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = SocialType.from(registrationId);
        return switch (socialType) {
            case GOOGLE -> new OauthGoogleResponse(oAuth2User.getAttributes());
            case KAKAO -> new OauthKakaoResponse(oAuth2User.getAttributes());
            default -> null;
        };
    }
}



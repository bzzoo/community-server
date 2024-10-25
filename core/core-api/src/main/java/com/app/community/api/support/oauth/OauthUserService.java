package com.app.community.api.support.oauth;

import com.app.community.domain.model.member.Member;
import com.app.community.domain.model.member.MemberRegister;
import com.app.community.domain.model.member.MemberSocial;
import com.app.community.domain.model.member.MemberSocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OauthUserService extends DefaultOAuth2UserService {

    private final MemberRegister memberRegister;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OauthResponse response = decideProvider(userRequest, oAuth2User);
        MemberSocial socialInfo = new MemberSocial(
                response.getUserId(),
                MemberSocialType.from(response.getProviderName())
        );
        Member member = getOrRegister(socialInfo, response);
        return new OauthUser(new OauthPrincipal(member.getId()));
    }

    private Member getOrRegister(MemberSocial socialInfo, OauthResponse response) {
       return memberRegister.register(socialInfo, response.getEmail(), response.getProfileImagePath());
    }

    private OauthResponse decideProvider(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        MemberSocialType memberSocialType = MemberSocialType.from(registrationId);
        return switch (memberSocialType) {
            case GOOGLE -> new OauthGoogleResponse(oAuth2User.getAttributes());
            case KAKAO -> new OauthKakaoResponse(oAuth2User.getAttributes());
            default -> null;
        };
    }
}



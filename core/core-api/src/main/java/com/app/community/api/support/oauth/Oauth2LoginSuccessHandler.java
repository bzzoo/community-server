package com.app.community.api.support.oauth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class Oauth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication
    ) throws IOException {
        OauthUser oauthUser = (OauthUser) authentication.getPrincipal();
        String token = jwtUtil.createJwt(oauthUser.principal().memberId(), oauthUser.getName());
        ResponseCookie cookie = ResponseCookie.from("mb_token", token)
                .maxAge(7 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("Lax")
                .httpOnly(true)
                .build();

        response.setHeader("Set-Cookie", cookie.toString());
        response.sendRedirect("http://localhost:3000/");
    }
}

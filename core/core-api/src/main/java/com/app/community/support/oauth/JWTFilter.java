package com.app.community.support.oauth;

import com.app.community.domain.agg.member.LoginMember;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = getJwtFromRequest(request);
        if(jwt != null && jwt.startsWith("-")){
            Long memberId = Long.valueOf(jwt.substring(1));
            Authentication authentication = new UsernamePasswordAuthenticationToken(new LoginMember(memberId), null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        else if (StringUtils.hasText(jwt)) {
            Long memberId = jwtUtil.getMemberId(jwt);
            Authentication authentication = new UsernamePasswordAuthenticationToken(new LoginMember(memberId), null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            Authentication anonymousAuth = new UsernamePasswordAuthenticationToken(
                    new LoginMember(-1L),
                    "anonymousUser",
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))
            );
            SecurityContextHolder.getContext().setAuthentication(anonymousAuth);
        }
        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.equals("/login");
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
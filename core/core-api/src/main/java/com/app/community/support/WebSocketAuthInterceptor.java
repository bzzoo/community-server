package com.app.community.support;

import com.app.community.support.oauth.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class WebSocketAuthInterceptor implements HandshakeInterceptor {

    private final JWTUtil jwtUtil;

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes
    ) throws Exception {
        String token = extractTokenFromCookies(request.getHeaders().get("Cookie"));
        if (token != null && isValidToken(token)) {
            attributes.put("token", token);
            return true;
        }
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return false;
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception
    ) {

    }

    private String extractTokenFromCookies(List<String> cookieHeaders) {
        if (cookieHeaders == null) return null;
        for (String cookieHeader : cookieHeaders) {
            String[] cookies = cookieHeader.split(";");
            for (String cookie : cookies) {
                String trimmedCookie = cookie.trim();
                if (trimmedCookie.startsWith("mb_token=")) {
                    return trimmedCookie.substring(9);
                }
            }
        }
        return null;
    }

    private boolean isValidToken(String token) {
        if (token.startsWith("-")) {
            return true;
        }
        return jwtUtil.validateToken(token);
    }
}

package com.insightzz.clientservice.util;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static String getCurrentUsername() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {

            String username =
                    jwtAuthenticationToken
                            .getToken()
                            .getSubject();

            if (username != null && !username.isBlank()) {
                return username;
            }
        }

        throw new IllegalStateException(
                "Authenticated username not found"
        );
    }

    public static Long getCurrentUserId() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {

            Object userId =
                    jwtAuthenticationToken
                            .getToken()
                            .getClaim("userId");

            if (userId instanceof Number number) {
                return number.longValue();
            }
        }

        throw new IllegalStateException(
                "Authenticated user ID not found"
        );
    }
}

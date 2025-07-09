package com.blood_donation.blood_donation.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("STAFF")) {
            // Nếu là STAFF, chuyển hướng đến /staff/dashboard
            response.sendRedirect("/staff/dashboard");
        } else if (roles.contains("ADMIN")) {
            // Nếu là ADMIN, vẫn chuyển hướng đến /dashboard (nơi có các link quản trị)
            response.sendRedirect("/dashboard");
        } else {
            // Các vai trò khác (MEMBER) cũng chuyển hướng đến /dashboard
            response.sendRedirect("/dashboard");
        }
    }
}
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

        // --- THAY ĐỔI Ở ĐÂY ---
        if (roles.contains("ADMIN")) {
            // Nếu là ADMIN, chuyển hướng đến trang báo cáo
            response.sendRedirect("/admin/reports");
        } else if (roles.contains("STAFF")) {
            // Nếu là STAFF, chuyển hướng đến dashboard của nhân viên
            response.sendRedirect("/staff/dashboard");
        } else {
            // Các vai trò khác (MEMBER) chuyển hướng đến dashboard chung
            response.sendRedirect("/");
        }
    }
}
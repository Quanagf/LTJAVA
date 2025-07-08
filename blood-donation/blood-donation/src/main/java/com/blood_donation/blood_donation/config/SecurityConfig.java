package com.blood_donation.blood_donation.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.blood_donation.blood_donation.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. Phân quyền cho các vai trò cụ thể
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/staff/**").hasAnyAuthority("STAFF", "ADMIN")
                        .requestMatchers("/member/**").hasAuthority("MEMBER")
                        .requestMatchers("/donations/**", "/requests/emergency/**").hasAuthority("MEMBER")
                        
                        // 2. Phân quyền cho các trang chung của người dùng đã đăng nhập
                        .requestMatchers("/dashboard", "/profile/**").authenticated() // <-- ĐÂY LÀ THAY ĐỔI QUAN TRỌNG

                        // 3. Các trang công khai cho tất cả mọi người
                        .requestMatchers("/", "/home", "/login", "/register", "/css/**", "/js/**", "/images/**", "/error", "/forgot-password", "/blogs/**", "/blood-info").permitAll()
                        
                        // 4. Bất kỳ yêu cầu nào khác đều cần đăng nhập
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .sessionRegistry(sessionRegistry())
                        .expiredUrl("/login?account_locked=true") 
                );

        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
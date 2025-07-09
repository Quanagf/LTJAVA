package com.blood_donation.blood_donation.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler; // Thêm import này
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Inject handler tùy chỉnh của bạn
    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Các quy tắc phân quyền giữ nguyên...
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/staff/**").hasAnyAuthority("STAFF", "ADMIN")
                        .requestMatchers("/member/blogs/**").hasAuthority("MEMBER")
                        .requestMatchers("/donations/**", "/requests/emergency/**").hasAuthority("MEMBER")
                        .requestMatchers("/dashboard", "/profile/**").authenticated()
                        .requestMatchers("/", "/home", "/login", "/register", "/css/**", "/js/**", "/images/**", "/error", "/forgot-password", "/blogs/**", "/blood-info").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        // THAY ĐỔI Ở ĐÂY:
                        // Xóa .defaultSuccessUrl(...) và thay bằng .successHandler(...)
                        .successHandler(customAuthenticationSuccessHandler) 
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
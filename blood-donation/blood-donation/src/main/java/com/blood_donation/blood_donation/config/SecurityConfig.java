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
                        // Public paths
                        .requestMatchers("/", "/home", "/login", "/register", "/css/**", "/js/**", "/images/**", "/error","/forgot-password").permitAll()
                        .requestMatchers("/blogs/**", "/blood-info").permitAll()
                        // Admin specific paths
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        // Staff specific paths
                        .requestMatchers("/staff/**").hasAnyAuthority("STAFF", "ADMIN")
                        // Member specific paths
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/donations/register", "/donations/history", "/donations/edit/**", "/donations/update").hasAuthority("MEMBER")
                        .requestMatchers("/requests/emergency/new", "/requests/emergency").hasAuthority("MEMBER")
                        .requestMatchers("/profile/**").hasAuthority("MEMBER")
                        .requestMatchers("/member/blogs/**").hasAuthority("MEMBER") // <-- Rule mới cho Member
                        // General authenticated paths
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
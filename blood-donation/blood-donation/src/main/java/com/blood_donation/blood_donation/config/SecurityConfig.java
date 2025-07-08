package com.blood_donation.blood_donation.config;

import com.blood_donation.blood_donation.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // Cấu hình bộ lọc bảo mật chính
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF để test với Postman, bật lại khi triển khai thực tế
                .authorizeHttpRequests(auth -> auth
                        // Các đường dẫn công khai
                        .requestMatchers("/", "/home", "/login", "/register", "/css/**", "/js/**", "/images/**", "/error").permitAll()
                        .requestMatchers("/blogs/**", "/blood-info").permitAll()

                        // Các đường dẫn yêu cầu đăng nhập
                        .requestMatchers("/dashboard", "/profile/**").authenticated()
                        .requestMatchers("/donations/**").authenticated()

                        // Các chức năng dành cho Member
                        .requestMatchers("/donations/register", "/requests/emergency").hasAuthority("ADMIN")

                        // Chức năng của Staff và Admin
                        .requestMatchers("/staff/**").hasAnyAuthority("STAFF", "ADMIN")

                        // Chức năng của Admin
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")

                        // Các request còn lại đều yêu cầu đăng nhập
                        .requestMatchers("/requests/emergency/**").hasAnyAuthority("MEMBER", "STAFF", "ADMIN")
                        .anyRequest().authenticated()

                )
                // Cấu hình form đăng nhập
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                // Cấu hình đăng xuất
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    /*
    // Nếu bạn dùng encode mật khẩu khi đăng ký:

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */

}

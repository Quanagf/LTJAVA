package com.blood_donation.blood_donation.config;

import com.blood_donation.blood_donation.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

    // Bean này tạo ra một đối tượng để mã hóa mật khẩu.
    // Chúng ta dùng BCrypt, một thuật toán mã hóa mạnh và phổ biến.
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
    
    // Bean này cấu hình chuỗi filter bảo mật, là nơi định nghĩa các quy tắc chính.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Tắt CSRF để dễ dàng test với Postman, khi deploy nên bật lại
            .authorizeHttpRequests(auth -> auth
                // --- CÁC ĐƯỜNG DẪN CÔNG KHAI (KHÔNG CẦN ĐĂNG NHẬP) ---
                .requestMatchers("/", "/home", "/login", "/register", "/css/**", "/js/**", "/images/**", "/error").permitAll()
                .requestMatchers("/blogs/**", "/blood-info").permitAll() // Cho phép xem blog và thông tin nhóm máu
                
                // --- CÁC ĐƯỜNG DẪN YÊU CẦU VAI TRÒ ---
                // Yêu cầu đăng nhập để truy cập các trang này
                .requestMatchers("/dashboard", "/profile/**").authenticated() 
                
                // Chức năng của Member
                .requestMatchers("/donations/register", "/requests/emergency").hasAuthority("MEMBER")
                
                // Chức năng của Staff (bao gồm cả Admin)
                .requestMatchers("/staff/**").hasAnyAuthority("STAFF", "ADMIN")
                
                // Chức năng của Admin
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                
                // Bất kỳ request nào khác chưa được định nghĩa ở trên đều yêu cầu đăng nhập
                .anyRequest().authenticated()
            )
            // --- CẤU HÌNH FORM ĐĂNG NHẬP ---
            .formLogin(form -> form
                .loginPage("/login") // Đường dẫn đến trang đăng nhập tùy chỉnh của bạn
                .loginProcessingUrl("/perform_login") // URL mà form đăng nhập sẽ gửi đến để xử lý
                .defaultSuccessUrl("/dashboard", true) // Trang chuyển đến sau khi đăng nhập thành công
                .failureUrl("/login?error=true") // Trang chuyển đến nếu đăng nhập thất bại
                .permitAll() // Cho phép tất cả mọi người truy cập trang đăng nhập
            )
            // --- CẤU HÌNH ĐĂNG XUẤT ---
            .logout(logout -> logout
                .logoutUrl("/perform_logout") // URL để thực hiện đăng xuất
                .logoutSuccessUrl("/login?logout=true") // Trang chuyển đến sau khi đăng xuất thành công
                .invalidateHttpSession(true) // Hủy session
                .deleteCookies("JSESSIONID") // Xóa cookie
                .permitAll()
            );

        return http.build();
    }
}
package com.blood_donation.blood_donation.service;

import com.blood_donation.blood_donation.dto.UserRegistrationDto;
import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUser(UserRegistrationDto registrationDto) {
        // Kiểm tra mật khẩu có khớp không
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu xác nhận không khớp!");
        }
        // Kiểm tra username đã tồn tại chưa
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại!");
        }
        // Kiểm tra email đã tồn tại chưa
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng!");
        }

        // Tạo đối tượng User từ DTO
        User user = new User();
        user.setFullName(registrationDto.getFullName());
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        // Gán vai trò mặc định
        user.setRole(User.Role.MEMBER);

        return userRepository.save(user);
    }
}
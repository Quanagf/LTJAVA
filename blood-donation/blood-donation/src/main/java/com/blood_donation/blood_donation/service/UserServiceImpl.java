package com.blood_donation.blood_donation.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blood_donation.blood_donation.dto.AdminUserCreationDto;
import com.blood_donation.blood_donation.dto.AdminUserEditDto;
import com.blood_donation.blood_donation.dto.PasswordChangeDto;
import com.blood_donation.blood_donation.dto.UserProfileDto;
import com.blood_donation.blood_donation.dto.UserRegistrationDto;
import com.blood_donation.blood_donation.entity.BloodType;
import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.repository.BloodTypeRepository;
import com.blood_donation.blood_donation.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private BloodTypeRepository bloodTypeRepository;

    @Override
    public User registerNewUser(UserRegistrationDto registrationDto) {
        if (registrationDto.getDateOfBirth() == null) {
            throw new RuntimeException("Vui lòng nhập ngày sinh của bạn.");
        }
        int age = Period.between(registrationDto.getDateOfBirth(), LocalDate.now()).getYears();
        if (age < 18) {
            throw new RuntimeException("Bạn chưa đủ 18 tuổi để đăng ký tài khoản và hiến máu.");
        }
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu xác nhận không khớp!");
        }
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại!");
        }
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng!");
        }

        User user = new User();
        user.setFullName(registrationDto.getFullName());
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setDateOfBirth(registrationDto.getDateOfBirth());
        user.setPhone(registrationDto.getPhone());
        user.setNationalId(registrationDto.getNationalId());
        user.setAddress(registrationDto.getAddress());
        user.setProvince(registrationDto.getProvince());

        // Gán nhóm máu nếu có
        if (registrationDto.getBloodTypeId() != null) {
            BloodType bloodType = bloodTypeRepository.findById(registrationDto.getBloodTypeId())
                    .orElse(null); // Bỏ qua nếu không tìm thấy, cho phép người dùng không biết nhóm máu
            user.setBloodType(bloodType);
        }

        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(User.Role.MEMBER);

        return userRepository.save(user);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User createUserByAdmin(AdminUserCreationDto creationDto) {
        if (userRepository.existsByUsername(creationDto.getUsername())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại!");
        }
        if (userRepository.existsByEmail(creationDto.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng!");
        }

        User user = new User();
        user.setFullName(creationDto.getFullName());
        user.setUsername(creationDto.getUsername());
        user.setEmail(creationDto.getEmail());
        user.setPassword(passwordEncoder.encode(creationDto.getPassword()));
        user.setRole(creationDto.getRole());

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void updateUserProfile(String username, UserProfileDto profileDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng!"));

        Optional<User> userWithNewEmail = userRepository.findByEmail(profileDto.getEmail());
        if (userWithNewEmail.isPresent() && !userWithNewEmail.get().getId().equals(user.getId())) {
            throw new RuntimeException("Email đã được sử dụng bởi một tài khoản khác.");
        }

        user.setFullName(profileDto.getFullName());
        user.setEmail(profileDto.getEmail());
        user.setNationalId(profileDto.getNationalId());
        user.setDateOfBirth(profileDto.getDateOfBirth());
        user.setPhone(profileDto.getPhone());
        user.setAddress(profileDto.getAddress());
        user.setProvince(profileDto.getProvince());

        if (user.getRole() == User.Role.STAFF || user.getRole() == User.Role.ADMIN) {
            user.setPosition(profileDto.getPosition());
        }

        userRepository.save(user);
    }

    @Override
    public void resetPasswordToDefault(String userEmail) {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String defaultPassword = passwordEncoder.encode("123456");
            user.setPassword(defaultPassword);
            userRepository.save(user);
        }
    }

    @Override
    public void changeUserPassword(String username, PasswordChangeDto dto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng."));

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu cũ không chính xác.");
        }

        if (!dto.getNewPassword().equals(dto.getConfirmNewPassword())) {
            throw new RuntimeException("Mật khẩu mới và mật khẩu xác nhận không khớp.");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void lockUser(Integer userIdToLock, String adminUsername) {
        User userToLock = userRepository.findById(userIdToLock)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng để khóa."));
        User adminUser = userRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy admin."));

        if (userToLock.getId().equals(adminUser.getId())) {
            throw new RuntimeException("Bạn không thể tự khóa tài khoản của chính mình.");
        }

        userToLock.setLocked(true);
        userRepository.save(userToLock);

        List<Object> principals = sessionRegistry.getAllPrincipals();
        for (Object principal : principals) {
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                if (userDetails.getUsername().equals(userToLock.getUsername())) {
                    List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal, false);
                    for (SessionInformation session : sessions) {
                        session.expireNow();
                    }
                }
            }
        }
    }

    @Override
    public void unlockUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng."));
        user.setLocked(false);
        userRepository.save(user);
    }

    @Override
    public void updateUserByAdmin(AdminUserEditDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng."));

        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setPhone(userDto.getPhone());
        user.setNationalId(userDto.getNationalId());
        user.setAddress(userDto.getAddress());
        user.setProvince(userDto.getProvince());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setPosition(userDto.getPosition());
        
        if (userDto.getBloodTypeId() != null) {
            BloodType bloodType = bloodTypeRepository.findById(userDto.getBloodTypeId())
                    .orElse(null);
            user.setBloodType(bloodType);
        } else {
            user.setBloodType(null);
        }

        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Integer userIdToDelete, String adminUsername) {
        User userToDelete = userRepository.findById(userIdToDelete)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng để xóa."));
        User adminUser = userRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy admin."));

        if (userToDelete.getId().equals(adminUser.getId())) {
            throw new RuntimeException("Bạn không thể tự xóa tài khoản của chính mình.");
        }

        userRepository.delete(userToDelete);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable, String role) {
        if (role != null && !role.isEmpty()) {
            try {
                User.Role userRole = User.Role.valueOf(role.toUpperCase());
                return userRepository.findByRole(userRole, pageable);
            } catch (IllegalArgumentException e) {
                return userRepository.findAll(pageable);
            }
        }
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
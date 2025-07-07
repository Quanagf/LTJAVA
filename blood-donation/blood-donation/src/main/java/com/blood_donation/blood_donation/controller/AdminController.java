package com.blood_donation.blood_donation.controller;

import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.blood_donation.blood_donation.dto.AdminUserCreationDto; // Import DTO mới
import org.springframework.web.bind.annotation.ModelAttribute; // Thêm import
import org.springframework.web.bind.annotation.PostMapping; // Thêm import
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Thêm import

@Controller
@RequestMapping("/admin") // Tất cả các request trong controller này sẽ bắt đầu bằng /admin
public class AdminController {

    @Autowired
    private UserService userService;

    // Ánh xạ tới /admin/users
    @GetMapping("/users")
    public String listUsers(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userService.findAllUsers(pageable);

        model.addAttribute("userPage", userPage);

        return "admin/user-list"; // Trả về file user-list.html trong thư mục con admin
    }

    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("userDto", new AdminUserCreationDto());
        // Đưa danh sách các vai trò vào model để hiển thị trong dropdown
        model.addAttribute("roles", User.Role.values());
        return "admin/user-add";
    }
     @PostMapping("/users/save")
    public String saveNewUser(@ModelAttribute("userDto") AdminUserCreationDto userDto,
                              RedirectAttributes redirectAttributes) {
        try {
            userService.createUserByAdmin(userDto);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm người dùng mới thành công!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            // Nếu lỗi, trả về lại form add và giữ lại dữ liệu đã nhập
            redirectAttributes.addFlashAttribute("userDto", userDto);
            return "redirect:/admin/users/add";
        }
        return "redirect:/admin/users";
    }
}
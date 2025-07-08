package com.blood_donation.blood_donation.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

import com.blood_donation.blood_donation.dto.AdminUserCreationDto;
import com.blood_donation.blood_donation.dto.AdminUserEditDto;
import com.blood_donation.blood_donation.dto.BlogCreationDto;
import com.blood_donation.blood_donation.entity.Blog;
import com.blood_donation.blood_donation.entity.BloodUnit;
import com.blood_donation.blood_donation.entity.DonationRegistration;
import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.repository.BloodTypeRepository;
import com.blood_donation.blood_donation.service.BlogService;
import com.blood_donation.blood_donation.service.BloodInventoryService;
import com.blood_donation.blood_donation.service.UserService; // Thêm import
import com.blood_donation.blood_donation.service.DonationService;
import com.blood_donation.blood_donation.service.EmergencyRequestService;
import com.blood_donation.blood_donation.entity.EmergencyRequest;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private BloodTypeRepository bloodTypeRepository;   

    @Autowired
    private EmergencyRequestService emergencyRequestService; 
    @Autowired
    private BloodInventoryService bloodInventoryService; 



    // --- QUẢN LÝ NGƯỜI DÙNG ---
    @GetMapping("/users")
    public String listUsers(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(required = false) String role,
                            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userService.findAllUsers(pageable, role);
        model.addAttribute("userPage", userPage);
        model.addAttribute("allRoles", User.Role.values());
        model.addAttribute("currentRole", role);
        return "admin/user-list";
    }

    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("userDto", new AdminUserCreationDto());
        model.addAttribute("roles", User.Role.values());
        return "admin/user-add";
    }

    @PostMapping("/users/save")
    public String saveNewUser(@ModelAttribute("userDto") AdminUserCreationDto userDto, RedirectAttributes redirectAttributes) {
        try {
            userService.createUserByAdmin(userDto);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm người dùng mới thành công!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("userDto", userDto);
            return "redirect:/admin/users/add";
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showUserEditForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        AdminUserEditDto dto = new AdminUserEditDto();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setUsername(user.getUsername());
        model.addAttribute("userDto", dto);
        model.addAttribute("allRoles", User.Role.values());
        return "admin/user-edit-form";
    }

    @PostMapping("/users/update")
    public String processUserUpdate(@ModelAttribute("userDto") AdminUserEditDto userDto, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUserByAdmin(userDto);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin người dùng thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/lock")
    public String lockUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Principal principal) {
        try {
            userService.lockUser(id, principal.getName());
            redirectAttributes.addFlashAttribute("successMessage", "Đã khóa tài khoản thành công.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/unlock")
    public String unlockUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        userService.unlockUser(id);
        redirectAttributes.addFlashAttribute("successMessage", "Đã mở khóa tài khoản thành công.");
        return "redirect:/admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Principal principal) {
        try {
            userService.deleteUser(id, principal.getName());
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa người dùng thành công.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/admin/users";
    }

    // --- DUYỆT BÀI VIẾT BLOG ---
    @GetMapping("/blogs/pending")
    public String showPendingBlogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Blog> pendingBlogs = blogService.findPendingBlogs(pageable);
        model.addAttribute("blogPage", pendingBlogs);
        return "admin/blog-approval";
    }
    
    @GetMapping("/blogs/review/{id}")
    public String showBlogReviewPage(@PathVariable("id") Integer id, Model model) {
        Blog blog = blogService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết."));
        model.addAttribute("blog", blog);
        return "admin/blog-review";
    }

    @PostMapping("/blogs/{id}/approve")
    public String approveBlog(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            blogService.approveBlog(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã duyệt và đăng bài viết thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/admin/blogs/pending";
    }

    @PostMapping("/blogs/{id}/reject")
    public String rejectBlog(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            blogService.rejectBlog(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã từ chối bài viết.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/admin/blogs/pending";
    }
    
    // --- QUẢN LÝ BLOG TOÀN DIỆN ---
    @GetMapping("/blogs")
    public String showBlogManagement(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer authorId,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Blog> blogPage = blogService.findAllBlogs(pageable, authorId);

        model.addAttribute("blogPage", blogPage);
        model.addAttribute("authors", userService.findAll()); // Lấy tất cả user để làm bộ lọc
        model.addAttribute("currentAuthorId", authorId);

        return "admin/blog-list";
    }

    @GetMapping("/blogs/edit/{id}")
    public String showEditBlogForm(@PathVariable("id") Integer id, Model model) {
        Blog blog = blogService.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết."));
        BlogCreationDto dto = new BlogCreationDto();
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        model.addAttribute("blogDto", dto);
        model.addAttribute("blogId", id);
        return "admin/blog-edit-form";
    }

    @PostMapping("/blogs/update/{id}")
    public String updateBlog(@PathVariable("id") Integer id, @ModelAttribute("blogDto") BlogCreationDto blogDto, RedirectAttributes redirectAttributes) {
        try {
            blogService.updateBlogByAdmin(id, blogDto);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật bài viết thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/delete/{id}")
    public String deleteBlog(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            blogService.deleteBlog(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa bài viết thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/donation-registrations")
    public String showAllDonationRegistrations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            // Thêm các tham số lọc
            @RequestParam(required = false) Integer bloodTypeId,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate availableDate,
            @RequestParam(required = false) DonationRegistration.Status status,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<DonationRegistration> registrationPage = donationService.findAllRegistrations(pageable, bloodTypeId, phone, availableDate, status);

        model.addAttribute("registrationPage", registrationPage);
        // Đưa các giá trị lọc hiện tại vào model để giữ lại trên form
        model.addAttribute("bloodTypes", bloodTypeRepository.findAll());
        model.addAttribute("allStatuses", DonationRegistration.Status.values());
        model.addAttribute("currentBloodTypeId", bloodTypeId);
        model.addAttribute("currentPhone", phone);
        model.addAttribute("currentAvailableDate", availableDate);
        model.addAttribute("currentStatus", status);

        return "admin/donation-registration-list";
    }
    
    @GetMapping("/emergency-requests")
    public String showEmergencyRequestList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            // Thêm các tham số lọc
            @RequestParam(required = false) Integer bloodTypeId,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) EmergencyRequest.Status status,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<EmergencyRequest> requestPage = emergencyRequestService.findAllRequests(pageable, bloodTypeId, phone, status);

        model.addAttribute("requestPage", requestPage);
        // Đưa các giá trị lọc hiện tại vào model để giữ lại trên form
        model.addAttribute("bloodTypes", bloodTypeRepository.findAll());
        model.addAttribute("allStatuses", EmergencyRequest.Status.values());
        model.addAttribute("currentBloodTypeId", bloodTypeId);
        model.addAttribute("currentPhone", phone);
        model.addAttribute("currentStatus", status);

        return "admin/emergency-request-list";
    }

    @GetMapping("/blood-units")
    public String showBloodUnitList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer bloodTypeId,
            @RequestParam(required = false) BloodUnit.Status status,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BloodUnit> unitPage = bloodInventoryService.findAllUnits(pageable, bloodTypeId, status);

        model.addAttribute("unitPage", unitPage);
        model.addAttribute("bloodTypes", bloodTypeRepository.findAll());
        model.addAttribute("allStatuses", BloodUnit.Status.values());
        model.addAttribute("currentBloodTypeId", bloodTypeId);
        model.addAttribute("currentStatus", status);

        return "admin/blood-unit-list";
    }

}
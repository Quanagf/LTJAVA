package com.blood_donation.blood_donation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.blood_donation.blood_donation.dto.BloodUnitDto;
import com.blood_donation.blood_donation.entity.BloodUnit;
import com.blood_donation.blood_donation.entity.DonationRegistration;
import com.blood_donation.blood_donation.entity.EmergencyRequest;
import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.repository.BloodTypeRepository;
import com.blood_donation.blood_donation.repository.BloodUnitRepository;
import com.blood_donation.blood_donation.repository.DonationRegistrationRepository;
import com.blood_donation.blood_donation.service.BloodInventoryService;
import com.blood_donation.blood_donation.service.EmergencyRequestService;
import com.blood_donation.blood_donation.service.RequestService;
import com.blood_donation.blood_donation.service.StaffDashboardService;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffDashboardService staffDashboardService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private EmergencyRequestService emergencyRequestService;

    @Autowired
    private BloodInventoryService inventoryService;

    @Autowired
    private BloodTypeRepository bloodTypeRepository;

    @Autowired
    private DonationRegistrationRepository donationRegistrationRepository;
    
    @Autowired
    private BloodUnitRepository bloodUnitRepository;

    // --- DASHBOARD ---
    @GetMapping("/dashboard")
    public String showStaffDashboard(Model model) {
        model.addAttribute("pendingRequests", staffDashboardService.getPendingEmergencyRequestCount());
        model.addAttribute("pendingDonors", staffDashboardService.getPendingDonationRegistrationCount());
        model.addAttribute("inventorySummary", staffDashboardService.getInventorySummary());
        model.addAttribute("recentRequests", staffDashboardService.getRecentPendingEmergencyRequests(5));
        return "staff/dashboard";
    }

    // --- DUYỆT ĐƠN ĐĂNG KÝ HIẾN MÁU ---
    @GetMapping("/requests")
    public String showRequestManagementPage(
            @RequestParam(name = "donorPage", defaultValue = "0") int donorPage,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(donorPage, size);
        Page<DonationRegistration> donationRegistrationPage = requestService.findPendingDonationRegistrations(pageable);
        model.addAttribute("donationRegistrationPage", donationRegistrationPage);
        return "staff/request-management";
    }

    // --- QUẢN LÝ QUY TRÌNH HIẾN MÁU (2 CỘT) ---
    @GetMapping("/donors/manage")
    public String showDonorManagementPage(
            @RequestParam(name = "approvedPage", defaultValue = "0") int approvedPage,
            @RequestParam(name = "contactedPage", defaultValue = "0") int contactedPage,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Pageable approvedPageable = PageRequest.of(approvedPage, size);
        Page<DonationRegistration> approvedDonors = requestService.findApprovedDonationRegistrations(approvedPageable);
        model.addAttribute("approvedDonorsPage", approvedDonors);
        Pageable contactedPageable = PageRequest.of(contactedPage, size);
        Page<DonationRegistration> contactedDonors = requestService.findContactedDonationRegistrations(contactedPageable);
        model.addAttribute("contactedDonorsPage", contactedDonors);
        return "staff/donor-management";
    }

    // --- LỊCH SỬ HIẾN MÁU ---
    @GetMapping("/donors/history")
    public String showDonorHistory(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DonationRegistration> historyPage = requestService.findCompletedDonations(pageable);
        model.addAttribute("historyPage", historyPage);
        return "staff/donor-history";
    }

    // --- QUẢN LÝ KHO MÁU ---
    @GetMapping("/inventory")
    public String showInventoryManagement(Model model) {
        model.addAttribute("inventorySummary", inventoryService.getInventorySummary());
        model.addAttribute("bloodTypes", bloodTypeRepository.findAll());
        model.addAttribute("newBloodUnit", new BloodUnitDto());
        return "staff/inventory-management";
    }

    @PostMapping("/inventory/add")
    public String addBloodUnit(@ModelAttribute("newBloodUnit") BloodUnitDto bloodUnitDto, RedirectAttributes redirectAttributes) {
        try {
            inventoryService.addNewBloodUnit(bloodUnitDto);
            redirectAttributes.addFlashAttribute("successMessage", "Đã thêm đơn vị máu vào kho thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/inventory";
    }

    @GetMapping("/inventory/details")
    public String showInventoryDetails(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BloodUnit> unitPage = inventoryService.findAllAvailableUnits(pageable);
        model.addAttribute("unitPage", unitPage);
        return "staff/inventory-details";
    }

    @PostMapping("/inventory/use/{id}")
    public String useBloodUnit(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            inventoryService.useBloodUnit(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã sử dụng 1 đơn vị máu thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/inventory/details";
    }
    
    @PostMapping("/inventory/delete/{id}")
    public String deleteBloodUnit(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            inventoryService.deleteBloodUnit(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa đơn vị máu thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/inventory/details";
    }

    // --- QUẢN LÝ YÊU CẦU MÁU KHẨN CẤP ---
    @GetMapping("/emergency-requests")
    public String showEmergencyRequestList(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmergencyRequest> requestPage = emergencyRequestService.findAllRequests(pageable);
        
        // Tạo map để chứa số lượng tồn kho cho từng loại máu
        Map<Integer, Long> inventoryMap = new HashMap<>();
        List<Integer> bloodTypeIds = requestPage.getContent().stream()
                                           .map(req -> req.getBloodType().getId())
                                           .distinct()
                                           .collect(Collectors.toList());
        for (Integer bloodTypeId : bloodTypeIds) {
            Long availableQuantity = bloodUnitRepository.getInventorySummaryByBloodType(bloodTypeId);
            inventoryMap.put(bloodTypeId, availableQuantity != null ? availableQuantity : 0L);
        }
        
        model.addAttribute("requestPage", requestPage);
        model.addAttribute("inventoryMap", inventoryMap);
        return "staff/emergency-request-list";
    }
    
    @GetMapping("/emergency-requests/process/{requestId}")
    public String showProcessRequestPage(@PathVariable("requestId") Integer requestId, Model model) {
        EmergencyRequest request = emergencyRequestService.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Yêu cầu không tồn tại"));
        
        if (request.getStatus() != EmergencyRequest.Status.PROCESSING) {
            // Chỉ xử lý các yêu cầu có trạng thái PROCESSING
            return "redirect:/staff/emergency-requests";
        }

        model.addAttribute("request", request);

        // Tìm những người đã đăng ký hiến máu phù hợp đã được duyệt
        List<User> potentialDonors = emergencyRequestService.findPotentialDonors(request.getBloodType().getId());

        model.addAttribute("potentialDonors", potentialDonors);
        
        return "staff/process-emergency-request";
    }

    // --- CÁC HÀNH ĐỘNG (ACTIONS) ---

    @PostMapping("/donations/{id}/approve")
    public String approveDonation(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            requestService.approveDonationRegistration(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã chấp thuận yêu cầu hiến máu thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/requests";
    }

    @PostMapping("/donations/{id}/reject")
    public String rejectDonation(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            requestService.rejectDonationRegistration(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã từ chối yêu cầu hiến máu.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/requests";
    }

    @PostMapping("/donations/{id}/contact")
    public String contactDonation(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            requestService.contactDonationRegistration(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã chuyển người hiến sang danh sách chờ hoàn tất.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/donors/manage";
    }

    @PostMapping("/donations/{id}/complete")
    public String completeDonation(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            requestService.completeDonation(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã hoàn tất quy trình hiến máu và cập nhật kho!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/donors/manage";
    }

    @PostMapping("/emergency-requests/{id}/approve")
    public String approveEmergencyRequest(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            emergencyRequestService.approveRequest(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã phê duyệt yêu cầu thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/emergency-requests";
    }

    @PostMapping("/emergency-requests/{id}/reject")
    public String rejectEmergencyRequest(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            emergencyRequestService.rejectRequest(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã từ chối yêu cầu.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/emergency-requests";
    }
    
    @PostMapping("/emergency-requests/{id}/complete")
    public String completeEmergencyRequest(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            emergencyRequestService.completeProcessingRequest(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã hoàn tất yêu cầu và trừ máu từ kho.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/emergency-requests";
    }
}
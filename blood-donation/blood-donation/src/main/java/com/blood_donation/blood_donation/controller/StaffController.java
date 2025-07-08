package com.blood_donation.blood_donation.controller;

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
import com.blood_donation.blood_donation.entity.DonationRegistration;
import com.blood_donation.blood_donation.entity.EmergencyRequest;
import com.blood_donation.blood_donation.repository.BloodTypeRepository;
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

    @GetMapping("/dashboard")
    public String showStaffDashboard(Model model) {
        model.addAttribute("pendingRequests", staffDashboardService.getPendingEmergencyRequestCount());
        model.addAttribute("pendingDonors", staffDashboardService.getPendingDonationRegistrationCount());
        model.addAttribute("inventorySummary", staffDashboardService.getInventorySummary());
        model.addAttribute("recentRequests", staffDashboardService.getRecentPendingEmergencyRequests(5));
        return "staff/dashboard";
    }

    @GetMapping("/inventory")
    public String showInventoryManagement(Model model) {
        model.addAttribute("inventorySummary", inventoryService.getInventorySummary());
        model.addAttribute("bloodTypes", bloodTypeRepository.findAll());
        model.addAttribute("newBloodUnit", new BloodUnitDto());
        return "staff/inventory-management";
    }

    @PostMapping("/inventory/add")
    public String addBloodUnit(@ModelAttribute("newBloodUnit") BloodUnitDto bloodUnitDto,
                               RedirectAttributes redirectAttributes) {
        try {
            inventoryService.addNewBloodUnit(bloodUnitDto);
            redirectAttributes.addFlashAttribute("successMessage", "Đã thêm đơn vị máu vào kho thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/inventory";
    }

    @GetMapping("/emergency-requests")
    public String showEmergencyRequestList(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmergencyRequest> requestPage = emergencyRequestService.findAllRequests(pageable);
        model.addAttribute("requestPage", requestPage);
        return "staff/emergency-request-list";
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

    @GetMapping("/requests")
    public String showRequestManagementPage(
            @RequestParam(name = "donorPage", defaultValue = "0") int donorPage,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<DonationRegistration> donationRegistrationPage = requestService.findPendingDonationRegistrations(PageRequest.of(donorPage, size));
        model.addAttribute("donationRegistrationPage", donationRegistrationPage);
        return "staff/request-management";
    }

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

    @GetMapping("/donors/approved")
    public String showApprovedDonorsPage(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DonationRegistration> approvedDonorsPage = requestService.findApprovedDonationRegistrations(pageable);
        model.addAttribute("approvedDonorsPage", approvedDonorsPage);
        return "staff/approved-donors";
    }

    @PostMapping("/donations/{id}/contact")
    public String contactDonation(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            requestService.contactDonationRegistration(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã cập nhật trạng thái 'Đã liên hệ' thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/donors/approved";
    }

    @GetMapping("/donors/contacted")
    public String showContactedDonorsPage(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DonationRegistration> contactedDonorsPage = requestService.findContactedDonationRegistrations(pageable);
        model.addAttribute("contactedDonorsPage", contactedDonorsPage);
        return "staff/contacted-donors";
    }

    @PostMapping("/donations/{id}/complete")
    public String completeDonation(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            requestService.completeDonation(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã hoàn tất quy trình hiến máu và cập nhật kho!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/staff/donors/contacted";
    }
}
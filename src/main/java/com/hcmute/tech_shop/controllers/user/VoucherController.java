package com.hcmute.tech_shop.controllers.user;

import com.hcmute.tech_shop.entities.HiddenFlag;
import com.hcmute.tech_shop.entities.User;
import com.hcmute.tech_shop.entities.Voucher;
import com.hcmute.tech_shop.services.HiddenFlagService;
import com.hcmute.tech_shop.services.UserService;
import com.hcmute.tech_shop.services.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@Controller
@RequestMapping("/user/vouchers")
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherService voucherService;
    private final UserService userService;
    private final HiddenFlagService hiddenFlagService;

    @GetMapping("/{voucherId}")
    public String viewVoucher(@PathVariable Long voucherId, 
                            @RequestHeader(value = "X-User-Role", required = false) String userRole,
                            Authentication authentication,
                            Model model) {
        try {
            // Get current user
            User currentUser = userService.findByUsername(authentication.getName());
            
            // Get voucher
            Voucher voucher = voucherService.getVoucherById(voucherId);
            
            // Check if voucher exists
            if (voucher != null) {
                // Add voucher details to model
                model.addAttribute("voucher", voucher);
                
                // Check for special voucher ID that contains flag
                if (voucherId == 999L) {
                    // Decode and validate user role from header
                    if (userRole != null) {
                        String decodedRole = new String(Base64.getDecoder().decode(userRole));
                        if (decodedRole.equals("ADMIN")) {
                            HiddenFlag flag = hiddenFlagService.getFlagByChallenge("broken_access_control");
                            model.addAttribute("flag", flag.getFlag());
                        }
                    }
                }
                
                return "user/voucher-details";
            }
            
            // If voucher doesn't exist, return error
            model.addAttribute("error", "Voucher not found");
            return "error";
            
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred");
            return "error";
        }
    }
} 
package com.smartparking.controller;

import com.smartparking.service.AdminService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/dashboard")
    public Map<String, Long> dashboard() {
        return service.dashboard();
    }
}

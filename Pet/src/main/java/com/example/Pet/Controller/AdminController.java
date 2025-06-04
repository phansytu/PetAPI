package com.example.Pet.Controller;

import com.example.Pet.Payload.AdminDto;
import com.example.Pet.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5174")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<AdminDto> login(@RequestBody AdminDto dto) {
        AdminDto admin = adminService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(admin);
    }
}

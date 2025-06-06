package com.example.Pet.Controller;

import com.example.Pet.Payload.AdminDto;
import com.example.Pet.Security.JwtAuthResponse;
import com.example.Pet.Security.JwtTokenHelper;
import com.example.Pet.Service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5174")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody AdminDto dto) {
        AdminDto adminDto = adminService.login(dto.getEmail(), dto.getPassword());
        String token = jwtTokenHelper.generateTokenEmail(adminDto.getEmail());

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setEmail(adminDto.getEmail());
        response.setRole("ROLE_ADMIN");

        return ResponseEntity.ok(response);
    }
}

package com.example.Pet.Impl;

import com.example.Pet.Entity.Admin;
import com.example.Pet.Payload.AdminDto;
import com.example.Pet.Reponsitories.AdminRepo;
import com.example.Pet.Service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AdminDto login(String email, String password) {
        Admin admin = adminRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy admin"));
        if (!admin.getPassword().equals(password)) {
            throw new BadCredentialsException("Mật khẩu không đúng");
        }
        return modelMapper.map(admin, AdminDto.class);
    }

    @Override
    public AdminDto getAdminByEmail(String email) {
        Admin admin = adminRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy admin"));
        return modelMapper.map(admin, AdminDto.class);
    }

}

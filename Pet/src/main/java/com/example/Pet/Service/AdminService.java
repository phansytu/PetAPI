package com.example.Pet.Service;

import com.example.Pet.Payload.AdminDto;

public interface AdminService {
    AdminDto login(String email, String password);
    AdminDto getAdminByEmail(String email);

}

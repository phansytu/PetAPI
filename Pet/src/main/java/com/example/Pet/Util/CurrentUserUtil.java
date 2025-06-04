package com.example.Pet.Util;

import com.example.Pet.Entity.User;
import com.example.Pet.Reponsitories.UserRepo;
import com.example.Pet.Security.JwtTokenHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserUtil {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    public User getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        } else {
            throw new RuntimeException("Token không hợp lệ hoặc thiếu");
        }

        String phone = jwtTokenHelper.getPhoneNumberFromToken(token);
        return userRepo.findByPhoneNumber(phone)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng từ token"));
    }
}

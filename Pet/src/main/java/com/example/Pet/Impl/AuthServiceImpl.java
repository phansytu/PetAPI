    package com.example.Pet.Impl;

    import com.example.Pet.Entity.User;
    import com.example.Pet.Reponsitories.UserRepo;
    import com.example.Pet.Security.JwtTokenHelper;
    import com.example.Pet.Service.AuthService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import java.time.LocalDateTime;


    @Service
    public class AuthServiceImpl implements AuthService {
        @Autowired private UserRepo userRepo;
        @Autowired private JwtTokenHelper jwtTokenHelper;


        @Override
        public void requestOtp(String fullName, String phoneNumber) {
            User user = userRepo.findByPhoneNumber(phoneNumber)
                    .orElseGet(() -> {
                        User u = new User();
                        u.setFullName(fullName);
                        u.setPhoneNumber(phoneNumber);
                        return u;
                    });

            String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
            user.setOtp(otp);
            user.setOtpGeneratedAt(LocalDateTime.now());
            userRepo.save(user);

            // TODO: gửi OTP qua SMS service; tạm log ra console:
            System.out.println("OTP for " + phoneNumber + ": " + otp);
        }

        @Override
        public String verifyOtp(String phoneNumber, String otp) {
            User user = userRepo.findByPhoneNumber(phoneNumber)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (user.getOtp() == null || !user.getOtp().equals(otp))
                throw new RuntimeException("Invalid OTP");

            if (user.getOtpGeneratedAt().isBefore(LocalDateTime.now().minusMinutes(5)))
                throw new RuntimeException("OTP expired");

            // Issue JWT
            return jwtTokenHelper.generateTokenPhone(phoneNumber);
        }

    }

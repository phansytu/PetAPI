    package com.example.Pet.Controller;

    import com.example.Pet.Service.AuthService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {

        @Autowired
        private AuthService authService;

        @PostMapping("/request-otp")
        public ResponseEntity<String> requestOtp(
                @RequestParam String fullName,
                @RequestParam String phoneNumber) {
            authService.requestOtp(fullName, phoneNumber);
            return ResponseEntity.ok("OTP sent");
        }

        @PostMapping("/verify-otp")
        public ResponseEntity<String> verifyOtp(
                @RequestParam String phoneNumber,
                @RequestParam String otp) {
            String token = authService.verifyOtp(phoneNumber, otp);
            return ResponseEntity.ok(token);
        }
    }

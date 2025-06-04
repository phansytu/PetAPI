package com.example.Pet.Service;

public interface AuthService {
    void requestOtp(String fullName, String phoneNumber);
    String verifyOtp(String phoneNumber, String otp);
}

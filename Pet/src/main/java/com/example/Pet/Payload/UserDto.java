package com.example.Pet.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter

public class UserDto {
    private Integer UserId;
    @NotEmpty
    @Size(min = 3, max = 50, message = "Tên phải có từ 3 đến 50 ký tự")
    private String fullName;
    @NotEmpty
    private String phoneNumber;
    private Boolean active = true;

    // OTP fields
    private String otp;
    private LocalDateTime otpGeneratedAt;

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpGeneratedAt() {
        return otpGeneratedAt;
    }

    public void setOtpGeneratedAt(LocalDateTime otpGeneratedAt) {
        this.otpGeneratedAt = otpGeneratedAt;
    }

    public Integer getId() {
        return UserId;
    }

    public void setId(Integer id) {
        this.UserId = id;
    }

    public @NotEmpty @Size(min = 3, max = 50, message = "Tên phải có từ 3 đến 50 ký tự") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotEmpty @Size(min = 3, max = 50, message = "Tên phải có từ 3 đến 50 ký tự") String fullName) {
        this.fullName = fullName;
    }

    public @NotEmpty String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotEmpty String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

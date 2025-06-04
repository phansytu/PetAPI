package com.example.Pet.Payload;

import com.example.Pet.Entity.Appointment;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
public class PaymentDto {
    private Long id;
    private Appointment appointment; // Gắn với lịch hẹn

    private Double amount;            // Số tiền thanh toán
    private String method;            // CASH, CREDIT_CARD, MOMO, etc.
    private String status;            // PAID, UNPAID, CANCELED

    private LocalDateTime paidAt;
}

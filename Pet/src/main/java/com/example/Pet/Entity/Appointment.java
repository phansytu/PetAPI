package com.example.Pet.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Appointment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
        private User user;           // Người đặt lịch

        @ManyToOne
        @JoinColumn(name = "pet_id")
        private Pet pet;             // Thú cưng được sử dụng dịch vụ

        @ManyToOne
        @JoinColumn(name = "service_id")
        private Services service;  // dịch vụ được sử dụng


        private LocalDateTime appointmentDate; // Thời gian đặt hẹn
        private String status;       // BOOKED, COMPLETED, CANCELED

        private String notes;        // Ghi chú thêm cho lịch hẹn
        @CreationTimestamp
        private LocalDateTime createdAt;

    }


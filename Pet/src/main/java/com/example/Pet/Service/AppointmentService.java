package com.example.Pet.Service;

import com.example.Pet.Payload.AppointmentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AppointmentService {
    AppointmentDto create(AppointmentDto appointmentDto);
    AppointmentDto update(AppointmentDto appointmentDto, Integer appointmentId);
    void delete(Integer appointmentId);
    AppointmentDto getById(Integer appointmentId);
    Page<AppointmentDto> getAll(int page, int size);
}

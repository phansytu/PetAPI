package com.example.Pet.Service;

import com.example.Pet.Payload.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    AppointmentDto create(AppointmentDto appointmentDto);
    AppointmentDto update(AppointmentDto appointmentDto, Integer appointmentId);
    void delete(Integer appointmentId);
    AppointmentDto getById(Integer appointmentId);
    List<AppointmentDto> getAll();
}

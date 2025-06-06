package com.example.Pet.Impl;

import com.example.Pet.Entity.Appointment;
import com.example.Pet.Payload.AppointmentDto;
import com.example.Pet.Reponsitories.AppointmentRepo;
import com.example.Pet.Reponsitories.PetRepo;
import com.example.Pet.Reponsitories.ServiceRepo;
import com.example.Pet.Reponsitories.UserRepo;
import com.example.Pet.Service.AppointmentService;
import com.example.Pet.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AppointmentImpl implements AppointmentService {
    @Autowired private AppointmentRepo appointmentRepo;
    @Autowired private UserRepo userRepo;
    @Autowired private PetRepo petRepo;
    @Autowired private ServiceRepo serviceRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AppointmentDto create(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointment.getAppointmentDate());

        appointment.setStatus(appointmentDto.getStatus());
        appointment.setUser(userRepo.findById(appointmentDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", appointmentDto.getUserId())));
        appointment.setPet(petRepo.findById(appointmentDto.getPetId()).orElseThrow(() -> new ResourceNotFoundException("Pet", "id", appointmentDto.getPetId())));
        appointment.setService(serviceRepo.findById(appointmentDto.getServiceId()).orElseThrow(() -> new ResourceNotFoundException("Service", "id", appointmentDto.getServiceId())));
        Appointment saved = appointmentRepo.save(appointment);
        return this.modelMapper.map(saved, AppointmentDto.class);
    }

    @Override
    public AppointmentDto update(AppointmentDto appointmentDto, Integer appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));

        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setStatus(appointmentDto.getStatus());
        appointment.setUser(userRepo.findById(appointmentDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", appointmentDto.getUserId())));
        appointment.setPet(petRepo.findById(appointmentDto.getPetId()).orElseThrow(() -> new ResourceNotFoundException("Pet", "id", appointmentDto.getPetId())));
        appointment.setService(serviceRepo.findById(appointmentDto.getServiceId()).orElseThrow(() -> new ResourceNotFoundException("Service", "id", appointmentDto.getServiceId())));

        Appointment updated = appointmentRepo.save(appointment);
        return modelMapper.map(updated, AppointmentDto.class);
    }

    @Override
    public void delete(Integer appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));
        appointmentRepo.delete(appointment);
    }

    @Override
    public AppointmentDto getById(Integer appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    @Override
    public List<AppointmentDto> getAll() {
        return appointmentRepo.findAll().stream()
                .map(a -> modelMapper.map(a, AppointmentDto.class))
                .collect(Collectors.toList());
    }
}

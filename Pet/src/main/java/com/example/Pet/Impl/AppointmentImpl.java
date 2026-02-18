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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());

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
    public Page<AppointmentDto> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Appointment> appointments = appointmentRepo.findAll(pageable);

        return appointments.map(a -> modelMapper.map(a, AppointmentDto.class));
    }

}

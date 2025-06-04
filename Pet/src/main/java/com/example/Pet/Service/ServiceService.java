package com.example.Pet.Service;

import com.example.Pet.Payload.ServiceDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ServiceService {
    List<ServiceDto> getAllServices();
    //Xem chi tiết dịch vụ
    ServiceDto getByIdService(Integer serviceId);
    ServiceDto createService(ServiceDto serviceDto);
    ServiceDto updateService(ServiceDto serviceDto,Integer serviceId);
    void deleteService(Integer serviceId);
}

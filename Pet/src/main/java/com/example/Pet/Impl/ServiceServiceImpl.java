package com.example.Pet.Impl;

import com.example.Pet.Entity.Services;
import com.example.Pet.Payload.ServiceDto;
import com.example.Pet.Reponsitories.ServiceRepo;
import com.example.Pet.Service.ServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepo serviceRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ServiceDto> getAllServices() {
        List<Services> services = this.serviceRepo.findAll();

        return services.stream().
                map(services1 -> modelMapper.map(services1, ServiceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDto getByIdService(Integer serviceId) {
        Services services = this.serviceRepo.findById(serviceId)
                .orElseThrow();
        return this.modelMapper.map(services, ServiceDto.class);
    }

    @Override
    public ServiceDto createService(ServiceDto serviceDto) {
        Services servicess = this.modelMapper.map(serviceDto, Services.class);
        Services services1 = this.serviceRepo.save(servicess);

        return this.modelMapper.map(services1, ServiceDto.class);
    }

    @Override
    public ServiceDto updateService(ServiceDto serviceDto, Integer serviceId) {
        Services services = this.serviceRepo.findById(serviceId)
                .orElseThrow();
        services.setName(serviceDto.getName());
        services.setActive(serviceDto.getActive());
        services.setDescription(serviceDto.getDescription());
        services.setPrice(serviceDto.getPrice());
        services.setDuration(serviceDto.getDuration());
        Services services1 = this.serviceRepo.save(services);
        return this.modelMapper.map(services1, ServiceDto.class);
    }

    @Override
    public void deleteService(Integer serviceId) {
        Services services = this.serviceRepo.findById(serviceId)
                .orElseThrow();
        this.serviceRepo.delete(services);

    }
}

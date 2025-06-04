package com.example.Pet.Controller;

import com.example.Pet.Payload.ApiResponse;
import com.example.Pet.Payload.ServiceDto;
import com.example.Pet.Payload.ServiceDto;
import com.example.Pet.Service.ServiceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/services")
@RestController
@CrossOrigin(origins = "http://localhost:5174")
public class ServiceController {
    @Autowired
    private ServiceService service;

    // cap nhat dich vu
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceDto> createService(@Valid @RequestBody ServiceDto serviceDto){
        return new ResponseEntity<>(service.createService(serviceDto), HttpStatus.OK);
    }
    @PutMapping("/{serviceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceDto> updateService(@Valid @RequestBody ServiceDto serviceDto,
                                                    @PathVariable Integer serviceId) {
        return ResponseEntity.ok(service.updateService(serviceDto, serviceId));
    }

    @DeleteMapping("/{serviceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteService(@PathVariable Integer serviceId) {
        service.deleteService(serviceId);
        return ResponseEntity.ok(new ApiResponse("Đã xóa dich vu thành công", true));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<List<ServiceDto>> getAllService() {
        return ResponseEntity.ok(service.getAllServices());
    }
}

package com.example.Pet.Controller;

import com.example.Pet.Payload.ApiResponse;
import com.example.Pet.Payload.AppointmentDto;
import com.example.Pet.Service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/")
    public ResponseEntity<AppointmentDto> create(@Valid @RequestBody AppointmentDto appointmentDto){
        return new ResponseEntity<>(appointmentService.create(appointmentDto), HttpStatus.OK);
    }
    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDto> update(@Valid @RequestBody AppointmentDto appointmentDto,
                                                 @PathVariable Integer appointmentId){
        return ResponseEntity.ok(appointmentService.update(appointmentDto, appointmentId));
    }
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer appointmentId){
        appointmentService.delete(appointmentId);
        return ResponseEntity.ok(new ApiResponse("Da xoa thanh cong", true));
    }
    @GetMapping("/")
    public ResponseEntity<List<AppointmentDto>> getAll(){
        return ResponseEntity.ok(appointmentService.getAll());
    }
    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDto> getByIdAppointment(@PathVariable Integer appointmentId){
        return ResponseEntity.ok(appointmentService.getById(appointmentId));
    }

}

package com.example.Pet.Controller;

import com.example.Pet.Payload.ApiResponse;
import com.example.Pet.Payload.PetDto;
import com.example.Pet.Service.PetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "http://localhost:5174")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/")
    public ResponseEntity<PetDto> createPet(@Valid @RequestBody PetDto petDto, HttpServletRequest request) {
        return new ResponseEntity<>(petService.createPet(petDto, request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{petId}")
    public ResponseEntity<PetDto> updatePet(@Valid @RequestBody PetDto petDto,
                                            @PathVariable Integer petId,
                                            HttpServletRequest request) {
        return ResponseEntity.ok(petService.updatePet(petDto, petId, request));
    }

    @DeleteMapping("/delete/{petId}")
    public ResponseEntity<ApiResponse> deletePet(@PathVariable Integer petId, HttpServletRequest request) {
        petService.deletePet(petId, request);
        return ResponseEntity.ok(new ApiResponse("Đã xóa thú cưng thành công", true));
    }

    @GetMapping
    public ResponseEntity<List<PetDto>> getAllPets(HttpServletRequest request) {
        return ResponseEntity.ok(petService.getAllPets(request));
    }

    @GetMapping("/searchName/{name}")
    public ResponseEntity<List<PetDto>> searchName(@PathVariable String name, HttpServletRequest request) {
        return ResponseEntity.ok(petService.searchNameHome(name, request));
    }

    @GetMapping("/searchKg/{key}")
    public ResponseEntity<List<PetDto>> searchByWeight(@PathVariable Double key, HttpServletRequest request) {
        return ResponseEntity.ok(petService.searchKg(key, request));
    }

    @GetMapping("/searchType/{keyword}")
    public ResponseEntity<List<PetDto>> searchByType(@PathVariable String keyword, HttpServletRequest request) {
        return ResponseEntity.ok(petService.searchType(keyword, request));
    }
}

package com.example.Pet.Service;

import com.example.Pet.Payload.PetDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PetService {
    PetDto createPet(PetDto petDto, HttpServletRequest request);
    PetDto updatePet(PetDto petDto, Integer petId, HttpServletRequest request);
    void deletePet(Integer petId, HttpServletRequest request);
    List<PetDto> getAllPets(HttpServletRequest request);
    List<PetDto> searchKg(Double key, HttpServletRequest request);
    List<PetDto> searchType(String keyword, HttpServletRequest request);
    List<PetDto> searchNameHome(String name, HttpServletRequest request);
}

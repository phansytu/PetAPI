package com.example.Pet.Impl;

import com.example.Pet.Entity.Pet;
import com.example.Pet.Entity.User;
import com.example.Pet.Payload.PetDto;
import com.example.Pet.Reponsitories.PetRepo;
import com.example.Pet.Service.PetService;
import com.example.Pet.Util.CurrentUserUtil;
import com.example.Pet.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PetDto createPet(PetDto petDto, HttpServletRequest request) {
        User owner = currentUserUtil.getCurrentUser(request);

        // KHÔNG map owner từ DTO để tránh lỗi
        Pet pet = modelMapper.map(petDto, Pet.class);
        pet.setOwner(owner); // Lấy từ JWT
        pet.setCreatedAt(LocalDateTime.now());

        Pet savedPet = petRepo.save(pet);
        return modelMapper.map(savedPet, PetDto.class);
    }


    @Override
    public PetDto updatePet(PetDto petDto, Integer petId, HttpServletRequest request) {
        User owner = currentUserUtil.getCurrentUser(request);

        Pet pet = petRepo.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", "PetId", petId));

        if (!pet.getOwner().getUserId().equals(owner.getUserId())) {
            throw new RuntimeException("Bạn không có quyền cập nhật thú cưng này!");
        }

        pet.setGender(petDto.getGender());
        pet.setNotes(petDto.getNotes());
        pet.setType(petDto.getType());
        pet.setWeight(petDto.getWeight());

        Pet updatedPet = petRepo.save(pet);
        return modelMapper.map(updatedPet, PetDto.class);
    }

    @Override
    public void deletePet(Integer petId, HttpServletRequest request) {
        User owner = currentUserUtil.getCurrentUser(request);

        Pet pet = petRepo.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", "PetId", petId));

        if (!pet.getOwner().getUserId().equals(owner.getUserId())) {
            throw new RuntimeException("Bạn không có quyền xóa thú cưng này!");
        }

        petRepo.delete(pet);
    }

    @Override
    public List<PetDto> getAllPets(HttpServletRequest request) {
        User owner = currentUserUtil.getCurrentUser(request);
        List<Pet> pets = petRepo.findAllByOwner(owner);
        return pets.stream()
                .map(pet -> modelMapper.map(pet, PetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDto> searchNameHome(String name, HttpServletRequest request) {
        User owner = currentUserUtil.getCurrentUser(request);
        List<Pet> pets = petRepo.searchByNameHome("%" + name + "%", owner);
        return pets.stream()
                .map(pet -> modelMapper.map(pet, PetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDto> searchKg(Double weight, HttpServletRequest request) {
        User owner = currentUserUtil.getCurrentUser(request);
        List<Pet> pets = petRepo.searchByWeight(weight, owner);
        return pets.stream()
                .map(pet -> modelMapper.map(pet, PetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDto> searchType(String type, HttpServletRequest request) {
        User owner = currentUserUtil.getCurrentUser(request);
        List<Pet> pets = petRepo.searchByType("%" + type + "%", owner);
        return pets.stream()
                .map(pet -> modelMapper.map(pet, PetDto.class))
                .collect(Collectors.toList());
    }
}

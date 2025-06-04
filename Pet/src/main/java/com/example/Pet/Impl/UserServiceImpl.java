package com.example.Pet.Impl;

import com.example.Pet.Entity.User;
import com.example.Pet.Payload.PetDto;
import com.example.Pet.Payload.UserDto;
import com.example.Pet.Reponsitories.PetRepo;
import com.example.Pet.Reponsitories.UserRepo;
import com.example.Pet.Service.PetService;
import com.example.Pet.Service.UserService;
import com.example.Pet.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        User userNew = this.userRepo.save(user);
        return this.modelMapper.map(userNew, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "User", "userId", userId
        ));
        System.out.println("before - update: " + user);
        user.setFullName(userDto.getFullName());
        user.setOtp(userDto.getOtp());
        user.setActive(userDto.getActive());
        user.setPhoneNumber(userDto.getPhoneNumber());

        User userNew = this.userRepo.save(user);
        System.out.println("after - update: " + userNew);
        return this.modelMapper.map(userNew, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getByIdUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "User", "userId", userId
        ));
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "userId", userId
                ));
        this.userRepo.delete(user);
    }
}



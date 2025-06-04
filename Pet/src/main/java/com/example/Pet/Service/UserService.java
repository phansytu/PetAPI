package com.example.Pet.Service;

import com.example.Pet.Payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    List<UserDto> getAllUser();
    UserDto getByIdUser(Integer userId);
    public void deleteUser(Integer userId);
}

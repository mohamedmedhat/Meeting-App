package com.example.demo.mapper;

import com.example.demo.dto.request.RegisterInputRequestDto;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.dto.response.RegisterResponseDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;


    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }


    public RegisterResponseDto toRegisterResponseDto(User user) {
        return new RegisterResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles()
        );
    }


    public User RegisterInputRequestToEntity(RegisterInputRequestDto dto) {
        String hashPassword = this.encodePassword(dto.getPassword());
        Set<String> upperCaseRoles = dto.getRoles().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(hashPassword);
        user.setRoles(upperCaseRoles);
        return user;
    }


    public User UserUpdateTOEntity(RegisterInputRequestDto dto, User user) {
        String hashPassword = this.encodePassword(dto.getPassword());
        Set<String> upperCaseRoles = dto.getRoles().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(hashPassword);
        user.setRoles(upperCaseRoles);
        return user;
    }


    public UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(
                user.getName(),
                user.getEmail(),
                user.getRoles()
        );
    }


    public LoginResponseDto toLoginResponseDto(UserResponseDto user, String token) {
        return new LoginResponseDto(
                user,
                token
        );
    }
}

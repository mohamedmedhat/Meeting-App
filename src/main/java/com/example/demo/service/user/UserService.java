package com.example.demo.service.user;

import com.example.demo.dto.request.LoginInputRequestDto;
import com.example.demo.dto.request.RegisterInputRequestDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.dto.response.RegisterResponseDto;
import com.example.demo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Async
    @Override
    public CompletableFuture<RegisterResponseDto> signUp(RegisterInputRequestDto userData) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                User user = this.userMapper.RegisterInputRequestToEntity(userData);
                User savedUser = this.userRepository.save(user);
                return this.userMapper.toRegisterResponseDto(savedUser);
            } catch (Exception e) {
                throw new RuntimeException("Error during sign-up: " + e.getMessage(), e);
            }
        });
    }

    @Async
    @Override
    public CompletableFuture<Boolean> validatePassword(String rawPassword, String encodedPassword) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.passwordEncoder.matches(rawPassword, encodedPassword);
            } catch (Exception e) {
                throw new RuntimeException("Error during password validation: " + e.getMessage(), e);
            }
        });
    }

    @Async
    @Override
    public CompletableFuture<LoginResponseDto> login(LoginInputRequestDto userData) {
        return CompletableFuture.supplyAsync(() -> {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userData.getEmail(), userData.getPassword()));
            return this.userRepository.findByEmail(userData.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }).thenCompose(user -> this.validatePassword(userData.getPassword(), user.getPassword()).thenApply(isPasswordValid -> {
            if (isPasswordValid) {
                String token = jwtUtil.generateToken(user);
                UserResponseDto userDto = this.userMapper.toUserResponseDto(user);
                return this.userMapper.toLoginResponseDto(userDto, token);
            }
            throw new RuntimeException("Invalid credentials");
        }));
    }


    @Override
    public User getUserById(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return this.userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all users: " + e.getMessage(), e);
        }
    }

    @Async
    @Override
    public CompletableFuture<RegisterResponseDto> updateUser(String id, RegisterInputRequestDto userData) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                User user = this.getUserById(id);
                User updateUser = this.userMapper.UserUpdateTOEntity(userData, user);
                User savedUser = this.userRepository.save(updateUser);
                return this.userMapper.toRegisterResponseDto(savedUser);
            } catch (Exception e) {
                throw new RuntimeException("Error during user update: " + e.getMessage(), e);
            }
        });
    }

    @Override
    public Boolean deleteUser(String id) {
        try {
            User user = this.getUserById(id);
            this.userRepository.delete(user);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error during user deletion: " + e.getMessage(), e);
        }
    }
}

package com.example.demo.controller;

import com.example.demo.dto.request.LoginInputRequestDto;
import com.example.demo.dto.request.RegisterInputRequestDto;
import com.example.demo.dto.response.RegisterResponseDto;
import com.example.demo.model.User;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.service.user.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users/")
public class UserController {
    private final IUserService userService;


    @PostMapping("auth/register")
    public CompletableFuture<RegisterResponseDto> register(@Valid @RequestBody RegisterInputRequestDto userData) {
        return this.userService.signUp(userData);
    }

    @PostMapping("auth/login")
    public CompletableFuture<LoginResponseDto> login(@Valid @RequestBody LoginInputRequestDto userData) {
        return this.userService.login(userData);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CompletableFuture<RegisterResponseDto> updateUser(@PathVariable("id") String id, @Valid @RequestBody RegisterInputRequestDto userData) {
        return this.userService.updateUser(id, userData);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean deleteUser(@PathVariable("id") String id) {
        return this.userService.deleteUser(id);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ORGANIZER')")
    public User getOne(@PathVariable("id") String id) {
        return this.userService.getUserById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAll() {
        return this.userService.getAllUsers();
    }
}

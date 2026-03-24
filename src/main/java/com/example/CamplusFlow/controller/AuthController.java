package com.example.CamplusFlow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.CamplusFlow.dto.LoginDTO;
import com.example.CamplusFlow.dto.UserRequestDTO;
import com.example.CamplusFlow.dto.UserResponseDTO;
import com.example.CamplusFlow.model.User;
import com.example.CamplusFlow.repository.UserRepository;
import com.example.CamplusFlow.security.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));

        // ✅ FIXED ROLE LOGIC
        if (dto.getRole() == null || dto.getRole().isEmpty()) {
            user.setRole("USER");   // default
        } else {
            user.setRole(dto.getRole());
        }

        User savedUser = repo.save(user);

        UserResponseDTO res = new UserResponseDTO();
        res.setId(savedUser.getId());
        res.setName(savedUser.getName());
        res.setEmail(savedUser.getEmail());
        res.setRole(savedUser.getRole());

        return res;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {

        User dbUser = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (encoder.matches(dto.getPassword(), dbUser.getPassword())) {
            return JwtUtil.generateToken(dbUser.getEmail(), dbUser.getRole());
        }

        throw new RuntimeException("Invalid credentials");
    }
}
package com.example.CamplusFlow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.CamplusFlow.dto.UserRequestDTO;
import com.example.CamplusFlow.dto.UserResponseDTO;
import com.example.CamplusFlow.model.User;
import com.example.CamplusFlow.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    // ✅ GET USERS (Pagination + Search)
    @GetMapping
    public List<UserResponseDTO> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword) {

        Pageable pageable = PageRequest.of(page, size);

        Page<User> userPage;

        if (keyword != null && !keyword.isEmpty()) {
            userPage = repo.findByNameContainingIgnoreCase(keyword, pageable);
        } else {
            userPage = repo.findAll(pageable);
        }

        return userPage.getContent().stream().map(user -> {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole());
            return dto;
        }).toList();
    }

    // ✅ UPDATE USER
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id,
                                      @RequestBody UserRequestDTO dto) {

        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        User updated = repo.save(user);

        UserResponseDTO res = new UserResponseDTO();
        res.setId(updated.getId());
        res.setName(updated.getName());
        res.setEmail(updated.getEmail());
        res.setRole(updated.getRole());

        return res;
    }

    // 🔥 DELETE USER
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        repo.deleteById(id);
        return "User deleted successfully";
    }
}
package com.example.CamplusFlow.dto;

public class UserRequestDTO {

    private String name;
    private String email;
    private String password;
    private String role;   // ✅ ADD THIS

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // ✅ FIXED ROLE GETTER/SETTER
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

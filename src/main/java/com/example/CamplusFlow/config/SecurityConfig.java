package com.example.CamplusFlow.config;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.CamplusFlow.security.JwtFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()

                .requestMatchers(HttpMethod.DELETE, "/users/**")
                .hasRole("ADMIN")

                .requestMatchers("/users/**")
                .hasAnyRole("USER", "ADMIN")

                .anyRequest().authenticated()
            )

            // ✅ MOVE HERE (OUTSIDE)
            .addFilterBefore(jwtFilter(),
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}
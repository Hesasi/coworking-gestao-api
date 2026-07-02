package com.coworking.api_gestaocoworking.dto;

public record LoginResponseDTO(String token, String tipo) {
    public LoginResponseDTO(String token) {
        this(token, "Bearer");
    }
}

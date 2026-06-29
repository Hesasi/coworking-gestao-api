package com.coworking.api_gestaocoworking.dto;

import com.coworking.api_gestaocoworking.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
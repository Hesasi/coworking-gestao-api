package com.coworking.api_gestaocoworking.dto;

import com.coworking.api_gestaocoworking.model.Filial;

public record FilialResponseDTO(Long id, String nome, String endereco) {
    public FilialResponseDTO(Filial filial) {
        this(filial.getId(), filial.getNome(), filial.getEndereco());
    }
}
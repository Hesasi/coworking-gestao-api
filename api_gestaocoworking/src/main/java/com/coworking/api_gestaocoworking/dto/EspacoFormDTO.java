package com.coworking.api_gestaocoworking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EspacoFormDTO(
        @NotBlank(message = "O nome do espaço é obrigatório.")
        String nome,

        @NotNull(message = "A capacidade é obrigatória.")
        @Positive(message = "A capacidade do espaço deve ser maior do que zero.")
        Integer capacidade
) {}
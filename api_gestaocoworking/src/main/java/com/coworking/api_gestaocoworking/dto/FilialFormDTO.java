package com.coworking.api_gestaocoworking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FilialFormDTO(
        @NotBlank(message = "O nome da filial é obrigatório.")
        @Size(max = 150, message = "O nome não pode exceder 150 caracteres.")
        String nome,

        @NotBlank(message = "O endereço da filial é obrigatório.")
        @Size(max = 255, message = "O endereço não pode exceder 255 caracteres.")
        String endereco
) {}
package com.coworking.api_gestaocoworking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginFormDTO(
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail informado deve ser válido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha
) {}

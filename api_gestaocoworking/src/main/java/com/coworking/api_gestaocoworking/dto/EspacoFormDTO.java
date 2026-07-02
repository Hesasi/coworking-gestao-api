package com.coworking.api_gestaocoworking.dto;

import com.coworking.api_gestaocoworking.model.TipoEspaco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record EspacoFormDTO(
        @NotBlank(message = "O nome do espaço é obrigatório.")
        String nome,

        @NotNull(message = "A capacidade é obrigatória.")
        @Positive(message = "A capacidade do espaço deve ser maior do que zero.")
        Integer capacidade,

        @NotNull(message = "O tipo do espaço é obrigatório.")
        TipoEspaco tipo,

        @NotNull(message = "O valor por hora é obrigatório.")
        @Positive(message = "O valor por hora deve ser maior do que zero.")
        BigDecimal valorHora
) {}

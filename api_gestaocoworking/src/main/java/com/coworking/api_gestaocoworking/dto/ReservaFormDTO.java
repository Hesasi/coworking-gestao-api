package com.coworking.api_gestaocoworking.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaFormDTO(
        @NotNull(message = "O espaço é obrigatório.")
        Long espacoId,

        @NotNull(message = "A data da reserva é obrigatória.")
        @FutureOrPresent(message = "A data da reserva não pode estar no passado.")
        LocalDate data,

        @NotNull(message = "O horário de início é obrigatório.")
        LocalTime horaInicio,

        @NotNull(message = "O horário de término é obrigatório.")
        LocalTime horaFim
) {}

package com.coworking.api_gestaocoworking.dto;

import com.coworking.api_gestaocoworking.model.Reserva;
import com.coworking.api_gestaocoworking.model.TipoEspaco;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaResponseDTO(
        Long id,
        String nomeUsuario,
        String nomeEspaco,
        TipoEspaco tipoEspaco,
        LocalDate data,
        LocalTime horaInicio,
        LocalTime horaFim,
        BigDecimal valorTotal
) {
    public ReservaResponseDTO(Reserva reserva) {
        this(
                reserva.getId(),
                reserva.getUsuario().getNome(),
                reserva.getEspaco().getNome(),
                reserva.getEspaco().getTipo(),
                reserva.getData(),
                reserva.getHoraInicio(),
                reserva.getHoraFim(),
                reserva.getValorTotal()
        );
    }
}

package com.coworking.api_gestaocoworking.dto;

import com.coworking.api_gestaocoworking.model.Espaco;
import com.coworking.api_gestaocoworking.model.TipoEspaco;

import java.math.BigDecimal;

public record EspacoResponseDTO(
        Long id,
        String nome,
        Integer capacidade,
        TipoEspaco tipo,
        BigDecimal valorHora,
        Long filialId,
        String nomeFilial
) {
    public EspacoResponseDTO(Espaco espaco) {
        this(
                espaco.getId(),
                espaco.getNome(),
                espaco.getCapacidade(),
                espaco.getTipo(),
                espaco.getValorHora(),
                espaco.getFilial().getId(),
                espaco.getFilial().getNome()
        );
    }
}

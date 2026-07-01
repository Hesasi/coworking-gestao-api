package com.coworking.api_gestaocoworking.dto;

import com.coworking.api_gestaocoworking.model.Espaco;

public record EspacoResponseDTO(Long id, String nome, Integer capacidade, Long filialId, String nomeFilial) {
    public EspacoResponseDTO(Espaco espaco) {
        this(
                espaco.getId(),
                espaco.getNome(),
                espaco.getCapacidade(),
                espaco.getFilial().getId(),
                espaco.getFilial().getNome()
        );
    }
}
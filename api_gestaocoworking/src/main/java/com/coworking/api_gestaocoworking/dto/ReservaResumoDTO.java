package com.coworking.api_gestaocoworking.dto;

import com.coworking.api_gestaocoworking.model.TipoEspaco;

import java.math.BigDecimal;
import java.util.Map;

public record ReservaResumoDTO(
        long totalReservas,
        Map<TipoEspaco, Long> reservasPorTipoDeEspaco,
        double duracaoMediaHoras,
        BigDecimal receitaTotalEstimada
) {}

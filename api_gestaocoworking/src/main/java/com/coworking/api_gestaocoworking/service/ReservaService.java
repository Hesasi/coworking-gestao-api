package com.coworking.api_gestaocoworking.service;

import com.coworking.api_gestaocoworking.dto.ReservaFormDTO;
import com.coworking.api_gestaocoworking.dto.ReservaResponseDTO;
import com.coworking.api_gestaocoworking.dto.ReservaResumoDTO;
import com.coworking.api_gestaocoworking.model.Espaco;
import com.coworking.api_gestaocoworking.model.Perfil;
import com.coworking.api_gestaocoworking.model.Reserva;
import com.coworking.api_gestaocoworking.model.TipoEspaco;
import com.coworking.api_gestaocoworking.model.Usuario;
import com.coworking.api_gestaocoworking.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final EspacoService espacoService;

    @Transactional
    public ReservaResponseDTO criar(Usuario usuarioLogado, ReservaFormDTO form) {
        if (!form.horaFim().isAfter(form.horaInicio())) {
            throw new IllegalArgumentException("O horário de término deve ser posterior ao horário de início.");
        }

        Espaco espaco = espacoService.buscarEntidadePura(form.espacoId());
        boolean conflito = reservaRepository.existeConflitoDeHorario(
                espaco.getId(), form.data(), form.horaInicio(), form.horaFim());
        if (conflito) {
            throw new IllegalArgumentException(
                    "Já existe uma reserva para este espaço no horário informado.");
        }

        BigDecimal horas = BigDecimal.valueOf(Duration.between(form.horaInicio(), form.horaFim()).toMinutes())
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
        BigDecimal valorTotal = espaco.getValorHora().multiply(horas);

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuarioLogado);
        reserva.setEspaco(espaco);
        reserva.setData(form.data());
        reserva.setHoraInicio(form.horaInicio());
        reserva.setHoraFim(form.horaFim());
        reserva.setValorTotal(valorTotal);

        return new ReservaResponseDTO(reservaRepository.save(reserva));
    }

    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> listar() {
        return reservaRepository.findAllComDetalhes().stream()
                .map(ReservaResponseDTO::new)
                .toList();
    }
    @Transactional(readOnly = true)
    public ReservaResumoDTO resumo() {
        List<Reserva> reservas = reservaRepository.findAllComDetalhes();

        long totalReservas = reservas.size();

        Map<TipoEspaco, Long> reservasPorTipo = reservas.stream()
                .collect(Collectors.groupingBy(r -> r.getEspaco().getTipo(), Collectors.counting()));

        double duracaoMediaHoras = reservas.stream()
                .mapToDouble(r -> Duration.between(r.getHoraInicio(), r.getHoraFim()).toMinutes() / 60.0)
                .average()
                .orElse(0.0);

        BigDecimal receitaTotal = reservas.stream()
                .map(Reserva::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ReservaResumoDTO(
                totalReservas,
                reservasPorTipo,
                Math.round(duracaoMediaHoras * 100.0) / 100.0,
                receitaTotal
        );
    }

    @Transactional
    public void deletar(Long id, Usuario usuarioLogado) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o ID: " + id));

        boolean isDono = reserva.getUsuario().getId().equals(usuarioLogado.getId());
        boolean isAdmin = usuarioLogado.getPerfil() == Perfil.ADMIN;

        if (!isDono && !isAdmin) {
            throw new AccessDeniedException("Você não tem permissão para cancelar esta reserva.");
        }

        reservaRepository.delete(reserva);
    }
}

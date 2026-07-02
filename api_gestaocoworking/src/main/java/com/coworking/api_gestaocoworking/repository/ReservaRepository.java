package com.coworking.api_gestaocoworking.repository;

import com.coworking.api_gestaocoworking.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("""
            SELECT COUNT(r) > 0 FROM Reserva r
            WHERE r.espaco.id = :espacoId
            AND r.data = :data
            AND r.horaInicio < :horaFim
            AND r.horaFim > :horaInicio
            """)
    boolean existeConflitoDeHorario(@Param("espacoId") Long espacoId,
                                    @Param("data") LocalDate data,
                                    @Param("horaInicio") LocalTime horaInicio,
                                    @Param("horaFim") LocalTime horaFim);
    @Query("SELECT r FROM Reserva r JOIN FETCH r.usuario JOIN FETCH r.espaco e JOIN FETCH e.filial")
    List<Reserva> findAllComDetalhes();
}

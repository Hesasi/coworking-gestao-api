package com.coworking.api_gestaocoworking.repository;

import com.coworking.api_gestaocoworking.model.Espaco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EspacoRepository extends JpaRepository<Espaco, Long> {
    @Query("SELECT e FROM Espaco e JOIN FETCH e.filial")
    List<Espaco> findAllComFilial();
}

package com.coworking.api_gestaocoworking.service;

import com.coworking.api_gestaocoworking.dto.EspacoFormDTO;
import com.coworking.api_gestaocoworking.dto.EspacoResponseDTO;
import com.coworking.api_gestaocoworking.model.Espaco;
import com.coworking.api_gestaocoworking.model.Filial;
import com.coworking.api_gestaocoworking.repository.EspacoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EspacoService {

    private final EspacoRepository espacoRepository;
    private final FilialService filialService;

    @Transactional(readOnly = true)
    public List<EspacoResponseDTO> listar() {
        return espacoRepository.findAll().stream()
                .map(EspacoResponseDTO::new)
                .toList();
    }

    @Transactional
    public EspacoResponseDTO criar(Long filialId, EspacoFormDTO form) {
        Filial filial = filialService.buscarEntidadePura(filialId);
        Espaco espaco = new Espaco(null, form.nome(), form.capacidade(), filial);
        return new EspacoResponseDTO(espacoRepository.save(espaco));
    }

    @Transactional
    public void deletar(Long id) {
        Espaco espaco = espacoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Espaço não encontrado com o ID: " + id));
        espacoRepository.delete(espaco);
    }
}
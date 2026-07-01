package com.coworking.api_gestaocoworking.service;

import com.coworking.api_gestaocoworking.dto.FilialFormDTO;
import com.coworking.api_gestaocoworking.dto.FilialResponseDTO;
import com.coworking.api_gestaocoworking.model.Filial;
import com.coworking.api_gestaocoworking.repository.FilialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilialService {

    private final FilialRepository filialRepository;

    @Transactional(readOnly = true)
    public List<FilialResponseDTO> listar() {
        return filialRepository.findAll().stream()
                .map(FilialResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public FilialResponseDTO buscarPorId(Long id) {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada com o ID: " + id));
        return new FilialResponseDTO(filial);
    }

    @Transactional(readOnly = true)
    public Filial buscarEntidadePura(Long id) {
        return filialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada com o ID: " + id));
    }

    @Transactional
    public FilialResponseDTO criar(FilialFormDTO form) {
        Filial filial = new Filial(null, form.nome(), form.endereco(), null);
        return new FilialResponseDTO(filialRepository.save(filial));
    }

    @Transactional
    public FilialResponseDTO atualizar(Long id, FilialFormDTO form) {
        Filial filial = buscarEntidadePura(id);
        filial.setNome(form.nome());
        filial.setEndereco(form.endereco());
        return new FilialResponseDTO(filial);
    }

    @Transactional
    public void deletar(Long id) {
        Filial filial = buscarEntidadePura(id);
        filialRepository.delete(filial);
    }
}
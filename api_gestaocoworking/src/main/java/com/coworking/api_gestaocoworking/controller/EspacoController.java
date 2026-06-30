package com.coworking.api_gestaocoworking.controller;

import com.coworking.api_gestaocoworking.model.Espaco;
import com.coworking.api_gestaocoworking.repository.EspacoRepository;
import com.coworking.api_gestaocoworking.repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espacos")
public class EspacoController {

    @Autowired
    private EspacoRepository espacoRepository;

    @Autowired
    private FilialRepository filialRepository;

    @GetMapping
    public List<Espaco> listar() {
        return espacoRepository.findAll();
    }

    @PostMapping("/filial/{filialId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Espaco criar(@PathVariable Long filialId, @RequestBody Espaco espaco) {
        return filialRepository.findById(filialId).map(filial -> {
            espaco.setFilial(filial);
            return espacoRepository.save(espaco);
        }).orElseThrow(() -> new IllegalArgumentException("Filial não encontrada."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return espacoRepository.findById(id)
                .map(espaco -> {
                    espacoRepository.delete(espaco);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new IllegalArgumentException("Espaço não encontrado com o ID: " + id));
    }
}
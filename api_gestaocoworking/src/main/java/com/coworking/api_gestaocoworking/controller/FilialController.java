package com.coworking.api_gestaocoworking.controller;

import com.coworking.api_gestaocoworking.model.Filial;
import com.coworking.api_gestaocoworking.repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filiais")
public class FilialController {

    @Autowired
    private FilialRepository repository;

    @GetMapping
    public List<Filial> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filial> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada com o ID: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Filial criar(@RequestBody Filial filial) {
        return repository.save(filial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filial> atualizar(@PathVariable Long id, @RequestBody Filial filialAtualizada) {
        return repository.findById(id)
                .map(filial -> {
                    filial.setNome(filialAtualizada.getNome());
                    filial.setEndereco(filialAtualizada.getEndereco());
                    return ResponseEntity.ok(repository.save(filial));
                })
                .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada com o ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(filial -> {
                    repository.delete(filial);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada com o ID: " + id));
    }
}
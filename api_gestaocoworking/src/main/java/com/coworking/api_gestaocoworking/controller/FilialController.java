package com.coworking.api_gestaocoworking.controller;

import com.coworking.api_gestaocoworking.dto.FilialFormDTO;
import com.coworking.api_gestaocoworking.dto.FilialResponseDTO;
import com.coworking.api_gestaocoworking.service.FilialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/filiais")
@RequiredArgsConstructor
public class FilialController {

    private final FilialService service;

    @GetMapping
    public ResponseEntity<List<FilialResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilialResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FilialResponseDTO> criar(@RequestBody @Valid FilialFormDTO form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilialResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid FilialFormDTO form) {
        return ResponseEntity.ok(service.atualizar(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
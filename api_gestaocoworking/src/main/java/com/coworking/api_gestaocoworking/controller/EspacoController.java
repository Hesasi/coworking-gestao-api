package com.coworking.api_gestaocoworking.controller;

import com.coworking.api_gestaocoworking.dto.EspacoFormDTO;
import com.coworking.api_gestaocoworking.dto.EspacoResponseDTO;
import com.coworking.api_gestaocoworking.service.EspacoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/espacos")
@RequiredArgsConstructor
public class EspacoController {

    private final EspacoService service;

    @GetMapping
    public ResponseEntity<List<EspacoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping("/filial/{filialId}")
    public ResponseEntity<EspacoResponseDTO> criar(@PathVariable Long filialId, @RequestBody @Valid EspacoFormDTO form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(filialId, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
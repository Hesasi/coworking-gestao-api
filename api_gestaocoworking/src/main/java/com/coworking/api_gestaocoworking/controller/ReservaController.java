package com.coworking.api_gestaocoworking.controller;

import com.coworking.api_gestaocoworking.dto.ReservaFormDTO;
import com.coworking.api_gestaocoworking.dto.ReservaResponseDTO;
import com.coworking.api_gestaocoworking.dto.ReservaResumoDTO;
import com.coworking.api_gestaocoworking.model.Usuario;
import com.coworking.api_gestaocoworking.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService service;

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> criar(@AuthenticationPrincipal Usuario usuarioLogado,
                                                    @RequestBody @Valid ReservaFormDTO form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(usuarioLogado, form));
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/resumo")
    public ResponseEntity<ReservaResumoDTO> resumo() {
        return ResponseEntity.ok(service.resumo());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable Long id) {
        service.deletar(id, usuarioLogado);
        return ResponseEntity.noContent().build();
    }
}

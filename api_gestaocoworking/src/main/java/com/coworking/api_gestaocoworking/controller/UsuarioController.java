package com.coworking.api_gestaocoworking.controller;

import com.coworking.api_gestaocoworking.dto.UsuarioFormDTO;
import com.coworking.api_gestaocoworking.dto.UsuarioResponseDTO;
import com.coworking.api_gestaocoworking.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody @Valid UsuarioFormDTO form, UriComponentsBuilder uriBuilder) {
        UsuarioResponseDTO response = usuarioService.cadastrar(form);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
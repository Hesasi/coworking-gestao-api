package com.coworking.api_gestaocoworking.service;

import com.coworking.api_gestaocoworking.dto.UsuarioFormDTO;
import com.coworking.api_gestaocoworking.dto.UsuarioResponseDTO;
import com.coworking.api_gestaocoworking.model.Endereco;
import com.coworking.api_gestaocoworking.model.Usuario;
import com.coworking.api_gestaocoworking.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioFormDTO form) {
        if (usuarioRepository.findByEmail(form.email()).isPresent()) {
            throw new IllegalArgumentException("Este e-mail já está cadastrado no sistema.");
        }
        Endereco endereco = new Endereco();
        endereco.setLogradouro(form.endereco().logradouro());
        endereco.setNumero(form.endereco().numero());
        endereco.setComplemento(form.endereco().complemento());
        endereco.setBairro(form.endereco().bairro());
        endereco.setCidade(form.endereco().city());
        endereco.setUf(form.endereco().uf());
        endereco.setCep(form.endereco().cep());
        Usuario usuario = new Usuario();
        usuario.setNome(form.nome());
        usuario.setEmail(form.email());
        usuario.setSenha(passwordEncoder.encode(form.senha()));
        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuario);
    }
}
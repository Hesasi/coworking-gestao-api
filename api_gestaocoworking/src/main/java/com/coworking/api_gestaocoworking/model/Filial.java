package com.coworking.api_gestaocoworking.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_filial")
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL)
    private List<Espaco> espacos;

    public Filial() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public List<Espaco> getEspacos() { return espacos; }
    public void setEspacos(List<Espaco> espacos) { this.espacos = espacos; }
}
package com.coworking.api_gestaocoworking.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_espaco")
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer capacidade;

    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    @JsonIgnore
    private Filial filial;

    public Espaco() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getCapacidade() { return capacidade; }
    public void setCapacidade(Integer capacidade) { this.capacidade = capacidade; }
    public Filial getFilial() { return filial; }
    public void setFilial(Filial filial) { this.filial = filial; }
}
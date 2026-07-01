package com.coworking.api_gestaocoworking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_filiais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 255)
    private String endereco;

    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL)
    private List<Espaco> espacos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filial filial = (Filial) o;
        return Objects.equals(id, filial.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
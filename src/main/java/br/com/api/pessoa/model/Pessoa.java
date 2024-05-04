package br.com.api.pessoa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "pessoa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, length = 80)
    private String nome;
    @Column(name = "sobre_nome", length = 80)
    private  String sobreNome;
    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;
    @Column(name = "endereco", nullable = false, length = 100)
    private String endereco;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return Objects.equals(id, pessoa.id) && Objects.equals(nome, pessoa.nome) && Objects.equals(sobreNome, pessoa.sobreNome) && Objects.equals(cpf, pessoa.cpf) && Objects.equals(endereco, pessoa.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobreNome, cpf, endereco);
    }
}

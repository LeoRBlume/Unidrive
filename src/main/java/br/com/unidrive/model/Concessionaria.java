package br.com.unidrive.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Concessionaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)")
    @Column(unique = true)
    private String cnpj;

    @Column(unique = true)
    private String nome;

    private String dt_criacao;

    public Concessionaria(String cnpj, String nome, String dt_criacao) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.dt_criacao = dt_criacao;
    }
}

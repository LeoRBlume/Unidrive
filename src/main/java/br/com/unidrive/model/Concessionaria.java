package br.com.unidrive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Concessionaria {

    @Id
    private String cnpj;

    @Column(unique = true)
    private String nome;

    private Timestamp dt_criacao;

    private Boolean cadastroAtivo;
}

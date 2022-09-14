package br.com.unidrive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carro {

    @Id
    private long id;
    private String nome;
    private String quilometragem;
    private String documentacao;
    private String cor;
    private String modelo;
    private String renovam;
    private String placa;
    private String status;
    private Timestamp dt_criacao;

}

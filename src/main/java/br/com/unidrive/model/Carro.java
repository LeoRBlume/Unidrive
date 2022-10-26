package br.com.unidrive.model;

import br.com.unidrive.controller.form.CarroForm;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String quilometragem;
    @Column(unique = true)
    private String documentacao;
    private String cor;
    private String modelo;
    @Column(unique = true)
    private String renovam;
    @Column(unique = true)
    private String placa;
    private String status;
    private Timestamp dt_criacao;
    @OneToOne
    private Concessionaria concessionaria;

    public static Carro cadastroCarroForm(CarroForm carroForm, Timestamp dt_criacao, Concessionaria concessionaria) {

        var carro = new Carro();

        carro.nome = carroForm.nome.toUpperCase();
        carro.quilometragem = carroForm.quilometragem;
        carro.documentacao = carroForm.documentacao;
        carro.cor = carroForm.cor;
        carro.modelo = carroForm.modelo.toUpperCase();
        carro.renovam = carroForm.renovam;
        carro.placa = carroForm.placa.toUpperCase();
        carro.status = "CRIADO";
        carro.dt_criacao = dt_criacao;
        carro.concessionaria = concessionaria;

        return carro;
    }
}

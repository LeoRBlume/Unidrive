package br.com.unidrive.model;

import br.com.unidrive.controller.form.CarroForm;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String quilometragem;
    @Column(unique = true)
    private String documentacao;
    private String cor;
    private String modelo;

    private String ano;
    @Column(unique = true)
    private String renavam;
    @Column(unique = true)
    private String placa;
    private String dt_criacao;
    @OneToOne
    private Concessionaria concessionaria;

    private String valor;

    public static Carro cadastroCarroForm(CarroForm carroForm, String dt_criacao, Concessionaria concessionaria) {

        var carro = new Carro();

        carro.marca = carroForm.marca.toUpperCase();
        carro.quilometragem = carroForm.quilometragem;
        carro.documentacao = carroForm.documentacao;
        carro.cor = carroForm.cor;
        carro.modelo = carroForm.modelo.toUpperCase();
        carro.renavam = carroForm.renavam;
        carro.placa = carroForm.placa.toUpperCase();
        carro.dt_criacao = dt_criacao;
        carro.concessionaria = concessionaria;
        carro.ano = carroForm.ano;
        carro.valor = carroForm.valor;

        return carro;
    }
}

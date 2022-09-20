package br.com.unidrive.controller.form;

import javax.validation.constraints.NotNull;

public class CarroForm {
    @NotNull
    private String nome;
    @NotNull
    private String quilometragem;
    @NotNull
    private String documentacao;
    @NotNull
    private String cor;
    @NotNull
    private String modelo;
    @NotNull
    private String renovam;
    @NotNull
    private String placa;
    @NotNull
    private String status;

}

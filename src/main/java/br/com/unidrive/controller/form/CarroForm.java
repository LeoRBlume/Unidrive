package br.com.unidrive.controller.form;

import javax.validation.constraints.NotNull;


public class CarroForm {
    @NotNull
    public String marca;
    @NotNull
    public String quilometragem;
    @NotNull
    public String documentacao;
    @NotNull
    public String cor;
    @NotNull
    public String modelo;
    @NotNull
    public String renavam;
    @NotNull
    public String placa;
    public String ano;

    public String valor;
}


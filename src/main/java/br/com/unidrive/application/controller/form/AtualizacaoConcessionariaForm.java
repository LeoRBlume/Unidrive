package br.com.unidrive.application.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AtualizacaoConcessionariaForm {

    @NotBlank(message = "Nome é obrigatorio!!")
    @Length(min = 10, max = 50, message = "Minimo de 10 caracteres e maximo de 50!!")
    private String nome;

}

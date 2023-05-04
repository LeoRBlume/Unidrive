package br.com.unidrive.application.controller.form;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class AtualizacaoUsuarioForm {

    @NotBlank(message = "Nome é obrigatorio!!")
    @Length(min = 3, max = 35, message = "Minimo de 3 letras e maximo de 35!!")
    private String nome;

    @NotBlank(message = "Nome é obrigatorio!!")
    @Length(min = 5, message = "Minimo 5 caracteres!!")
    private String email;

    @NotBlank(message = "Senha é obrigatorio!!")
    @Length(min = 7, message = "Senha só é valida com mais de 7 caractres")
    private String senha;

}

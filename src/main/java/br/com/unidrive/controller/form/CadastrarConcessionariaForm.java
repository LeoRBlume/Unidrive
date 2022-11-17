package br.com.unidrive.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarConcessionariaForm {

    @Pattern(regexp = "(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)")
    private String cnpj;
    private String nomeFantasia;
    private String email;

    private String telefone;

    private EnderecoForm enderecoForm;

}

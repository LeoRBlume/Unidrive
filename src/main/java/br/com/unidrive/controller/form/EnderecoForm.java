package br.com.unidrive.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoForm {

    private String endereco;
    private String numero;
    private String cep;
    private String complemento;

}

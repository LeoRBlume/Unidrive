package br.com.unidrive.domain;

import br.com.unidrive.controller.form.EnderecoForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;
    private String numero;
    private String cep;
    private String complemento;

    public Endereco(EnderecoForm enderecoForm) {
        this.endereco = enderecoForm.getEndereco();
        this.numero = enderecoForm.getNumero();
        this.cep = enderecoForm.getCep();
        this.complemento = enderecoForm.getComplemento();
    }
}

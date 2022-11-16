package br.com.unidrive.model;

import br.com.unidrive.controller.form.CadastrarConcessionariaForm;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Concessionaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)")
    @Column(unique = true)
    private String cnpj;

    @Column(unique = true)
    private String nomeFantasia;

    private String email;

    private String telefone;

    private String dt_criacao;

    @OneToOne
    private Endereco endereco;

    public Concessionaria(CadastrarConcessionariaForm cadastrarConcessionariaForm, String time, Endereco endereco) {
        this.cnpj = cadastrarConcessionariaForm.getCnpj();
        this.nomeFantasia = cadastrarConcessionariaForm.getNomeFantasia();
        this.email = cadastrarConcessionariaForm.getEmail();
        this.telefone = cadastrarConcessionariaForm.getTelefone();
        this.dt_criacao = time;
        this.endereco = endereco;

    }
}

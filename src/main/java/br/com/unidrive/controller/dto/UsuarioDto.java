package br.com.unidrive.controller.dto;


import br.com.unidrive.domain.Concessionaria;
import br.com.unidrive.domain.Endereco;
import br.com.unidrive.domain.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {

    private String nome;
    private String email;
    private String senha;

    private String cpf;

    private String telefone;

    private String cnh;

    private Endereco endereco;

    private Concessionaria concessionaria;

    public static UsuarioDto converter(Usuario usuario) {
        return new UsuarioDto(usuario.getNome(), usuario.getEmail(), "Senha não pode ser retornada", usuario.getCpf(), usuario.getTelefone(), usuario.getCnh(),
                usuario.getEndereco(), usuario.getConcessionaria());
    }

    public UsuarioDto(String nome, String email, String senha, String cpf, String telefone, String cnh, Endereco endereco, Concessionaria concessionaria) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.concessionaria = concessionaria;
        this.cnh = cnh;
    }

    public void converterAtualizacoes(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        if (this.senha == null) {

            this.setSenha("Senha inalterada");
        } else this.setSenha("Senha alterada com sucesso!!");
    }
}

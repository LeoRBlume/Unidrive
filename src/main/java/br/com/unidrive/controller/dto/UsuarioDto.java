package br.com.unidrive.controller.dto;


import br.com.unidrive.model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {

    private String nome;
    private String email;
    private String senha;

    public static UsuarioDto converter(Usuario usuario) {
        return new UsuarioDto(usuario.getNome(), usuario.getEmail(), "Senha não pode ser retornada");
    }

    public UsuarioDto(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void converterAtualizacoes(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        if (this.senha == null){

            this.setSenha("Senha inalterada");
        }
        else this.setSenha("Senha alterada com sucesso!!");
    }
    @Override
    public String toString() {
        return "UsuarioDto{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}

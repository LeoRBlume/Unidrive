package br.com.unidrive.domain.contract.useCase;

import br.com.unidrive.controller.form.UsuarioForm;
import org.springframework.http.ResponseEntity;

public interface UsuarioUseCase {

    public ResponseEntity<String> cadastrarUsuario(UsuarioForm usuarioForm);

    public ResponseEntity<?> deletarUsuario(String token);

    public ResponseEntity<?> obterUsuario(String token);

}

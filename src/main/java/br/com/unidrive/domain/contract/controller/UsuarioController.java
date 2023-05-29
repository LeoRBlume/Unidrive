package br.com.unidrive.domain.contract.controller;

import br.com.unidrive.application.controller.form.UsuarioForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

public interface UsuarioController {

    public ResponseEntity<?> obterUsuario(@RequestHeader(value = "Authorization") String token);

    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm);

    public ResponseEntity deletarUsuario(@RequestHeader(value = "Authorization") String token);


}

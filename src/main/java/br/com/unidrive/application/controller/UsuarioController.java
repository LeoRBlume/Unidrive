package br.com.unidrive.application.controller;


import br.com.unidrive.application.controller.form.UsuarioForm;
import br.com.unidrive.domain.contract.useCase.UsuarioUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioUseCase useCase;

    @GetMapping
    public ResponseEntity<?> obterUsuario(@RequestHeader(value = "Authorization") String token) {

        return useCase.obterUsuario(token);

    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {

        return useCase.cadastrarUsuario(usuarioForm);

    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletarUsuario(@RequestHeader(value = "Authorization") String token) {

        return useCase.deletarUsuario(token);

    }
}

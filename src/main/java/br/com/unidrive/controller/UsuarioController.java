package br.com.unidrive.controller;


import br.com.unidrive.controller.dto.UsuarioDto;
import br.com.unidrive.controller.form.AtualizacaoUsuarioForm;
import br.com.unidrive.controller.form.UsuarioForm;
import br.com.unidrive.useCase.UsuarioUseCase;
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
    public ResponseEntity<UsuarioDto> obterUsuario(@RequestHeader(value = "Authorization") String token) {

        return useCase.obterUsuario(token);

    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {

        return useCase.cadastrarUsuario(usuarioForm);

    }

    @PutMapping
    public ResponseEntity<UsuarioDto> atualizarUsuario(@RequestHeader(value = "Authorization") String token, @RequestBody AtualizacaoUsuarioForm form) {

        return useCase.atualizarCadastro(token, form);

    }

}

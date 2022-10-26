package br.com.unidrive.controller;


import br.com.unidrive.controller.dto.UsuarioDto;
import br.com.unidrive.controller.form.AtualizacaoUsuarioForm;
import br.com.unidrive.controller.form.UsuarioForm;
import br.com.unidrive.useCase.UsuarioUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioUseCase useCase;

    @GetMapping
    public ResponseEntity<UsuarioDto> obterUsuario(@RequestHeader(value = "Authorization") String token) {
        LOGGER.info("Metodo para retornar o usuario...");
        ResponseEntity<UsuarioDto> response = useCase.obterUsuario(token);
        LOGGER.info("Usuario retornado!");
        return response;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {
        LOGGER.info("Chamando endpoint para cadastrar usuario...");
        ResponseEntity<String> response = useCase.cadastrarUsuario(usuarioForm);
        LOGGER.info("Metodo para cadastrar o usuario encerrado!\n");
        return response;
    }

    @PutMapping
    public ResponseEntity<UsuarioDto> atualizarUsuario(@RequestHeader(value = "Authorization") String token, @RequestBody AtualizacaoUsuarioForm form) {
        LOGGER.info("Chamando endpoint para atualizar cadastro do usuario...");
        ResponseEntity<UsuarioDto> response = useCase.atualizarCadastro(token, form);
        LOGGER.info("Metodo para cadastrar o usuario encerrado!");
        return response;
    }

}

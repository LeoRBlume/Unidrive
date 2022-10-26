package br.com.unidrive.controller;

import br.com.unidrive.controller.form.AtualizacaoConcessionariaForm;
import br.com.unidrive.controller.form.CadastrarConcessionariaForm;
import br.com.unidrive.model.Carro;
import br.com.unidrive.useCase.ConcessionariaUseCase;
import br.com.unidrive.useCase.UsuarioUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/concessionaria")
public class ConcessionariaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcessionariaController.class);

    @Autowired
    ConcessionariaUseCase concessionariaUseCase;

    @Autowired
    UsuarioUseCase usuarioUseCase;

    @GetMapping
    public ResponseEntity obterConcessionaria(@RequestHeader(value = "Authorization") String token) {

        LOGGER.info("Obtendo usuario...");
        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        LOGGER.info("Usuario obetido!");


        LOGGER.info("Obtendo concessionaria...");
        var response = concessionariaUseCase.obterConcessionaria(usuario);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrarConcessionaria(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody CadastrarConcessionariaForm cadastrarConcessionariaForm) {

        LOGGER.info("Obtendo usuario...");
        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        LOGGER.info("Usuario obetido!");

        LOGGER.info("Metodo para cadastrar concessionaria...");
        var concessionariaCadastrada = concessionariaUseCase.cadastrarConcessionaria(cadastrarConcessionariaForm);
        LOGGER.info("Cadastro da concessionaria efetuado!");

        LOGGER.info("Metodo para vincular concessionaria a um usuario...");
        var response = usuarioUseCase.vincularConcessionaria(usuario, concessionariaCadastrada);
        LOGGER.info("Concessionarfia vinculada com sucesso!");


        return ResponseEntity.status(response.getStatus().value()).body(response.getResposta());
    }

    @PutMapping
    public ResponseEntity<?> atualizarConcessionaria(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody AtualizacaoConcessionariaForm atualizacaoConcessionariaForm) {

        LOGGER.info("Obtendo usuario...");
        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        LOGGER.info("Usuario obetido!");

        return concessionariaUseCase.atualizarConcessionaria(usuario, atualizacaoConcessionariaForm);
    }

    @GetMapping
    @RequestMapping("/carros")
    public List<Carro> obterCarrosConcessionaria(@RequestHeader(value = "Authorization") String token){

        LOGGER.info("Obtendo usuario...");
        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        LOGGER.info("Usuario obetido!");

        return concessionariaUseCase.obterCarrosConcessionaria(usuario);

    }

}

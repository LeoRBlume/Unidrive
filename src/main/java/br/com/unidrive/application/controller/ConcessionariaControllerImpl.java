package br.com.unidrive.application.controller;

import br.com.unidrive.application.controller.form.AtualizacaoConcessionariaForm;
import br.com.unidrive.application.controller.form.CadastrarConcessionariaForm;
import br.com.unidrive.domain.contract.controller.ConcessionariaController;
import br.com.unidrive.domain.model.Carro;
import br.com.unidrive.application.useCase.ConcessionariaUseCaseImpl;
import br.com.unidrive.application.useCase.UsuarioUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/concessionaria")
public class ConcessionariaControllerImpl implements ConcessionariaController {
    @Autowired
    ConcessionariaUseCaseImpl concessionariaUseCaseImpl;

    @Autowired
    UsuarioUseCaseImpl usuarioUseCase;

    @GetMapping
    public ResponseEntity obterConcessionaria(@RequestHeader(value = "Authorization") String token) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        var response = concessionariaUseCaseImpl.obterConcessionaria(usuario);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrarConcessionaria(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody CadastrarConcessionariaForm cadastrarConcessionariaForm) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        var concessionariaCadastrada = concessionariaUseCaseImpl.cadastrarConcessionaria(cadastrarConcessionariaForm);
        var response = usuarioUseCase.vincularConcessionaria(usuario, concessionariaCadastrada);

        return ResponseEntity.status(response.getStatus().value()).body(response.getResposta());
    }

    @PutMapping
    public ResponseEntity<?> atualizarConcessionaria(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody AtualizacaoConcessionariaForm atualizacaoConcessionariaForm) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        return concessionariaUseCaseImpl.atualizarConcessionaria(usuario, atualizacaoConcessionariaForm);

    }

    @GetMapping("/carros")
    public List<Carro> obterCarrosConcessionaria(@RequestHeader(value = "Authorization") String token) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        return concessionariaUseCaseImpl.obterCarrosConcessionaria(usuario);

    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletarConcessionaria(@RequestHeader(value = "Authorization") String token){

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        return concessionariaUseCaseImpl.deletarConcessionaria(usuario);

    }

}

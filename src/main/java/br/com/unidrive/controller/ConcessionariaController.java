package br.com.unidrive.controller;

import br.com.unidrive.controller.form.AtualizacaoConcessionariaForm;
import br.com.unidrive.controller.form.CadastrarConcessionariaForm;
import br.com.unidrive.model.Carro;
import br.com.unidrive.useCase.ConcessionariaUseCase;
import br.com.unidrive.useCase.UsuarioUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/concessionaria")
public class ConcessionariaController {
    @Autowired
    ConcessionariaUseCase concessionariaUseCase;

    @Autowired
    UsuarioUseCase usuarioUseCase;

    @GetMapping
    public ResponseEntity obterConcessionaria(@RequestHeader(value = "Authorization") String token) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        var response = concessionariaUseCase.obterConcessionaria(usuario);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrarConcessionaria(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody CadastrarConcessionariaForm cadastrarConcessionariaForm) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        var concessionariaCadastrada = concessionariaUseCase.cadastrarConcessionaria(cadastrarConcessionariaForm);
        var response = usuarioUseCase.vincularConcessionaria(usuario, concessionariaCadastrada);

        return ResponseEntity.status(response.getStatus().value()).body(response.getResposta());
    }

    @PutMapping
    public ResponseEntity<?> atualizarConcessionaria(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody AtualizacaoConcessionariaForm atualizacaoConcessionariaForm) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        return concessionariaUseCase.atualizarConcessionaria(usuario, atualizacaoConcessionariaForm);

    }

    @GetMapping("/carros")
    public List<Carro> obterCarrosConcessionaria(@RequestHeader(value = "Authorization") String token) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        return concessionariaUseCase.obterCarrosConcessionaria(usuario);

    }

}

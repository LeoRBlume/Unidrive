package br.com.unidrive.controller;

import br.com.unidrive.controller.form.CarroForm;
import br.com.unidrive.controller.form.AtualizacaoCarroForm;
import br.com.unidrive.domain.Carro;
import br.com.unidrive.application.useCase.CarroUseCase;
import br.com.unidrive.application.useCase.ConcessionariaUseCase;
import br.com.unidrive.application.useCase.UsuarioUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {
    @Autowired
    CarroUseCase carroUseCase;

    @Autowired
    UsuarioUseCaseImpl usuarioUseCase;

    @Autowired
    ConcessionariaUseCase concessionariaUseCase;

    @PostMapping
    public ResponseEntity cadastrarCarro(@RequestBody @Valid List<CarroForm> carroForm, @RequestHeader(value = "Authorization") String token) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        return carroUseCase.cadastrarCarros(carroForm, usuario);

    }

    @DeleteMapping("/{carroId}")
    public ResponseEntity deletarCarro(@RequestHeader(value = "Authorization") String token, @PathVariable String carroId) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        var concessionaria = concessionariaUseCase.obterConcessionaria(usuario);


        return carroUseCase.deletarCarros(carroId, concessionaria);

    }

    @GetMapping("/{carroId}")
    public ResponseEntity obterCarro(@PathVariable String carroId) {

        return carroUseCase.obterCarroPorId(carroId);
    }


    @GetMapping
    public List<Carro> obterTodosCarros() {
        return carroUseCase.obterTodosCarros();
    }

    @PutMapping("/{carroId}")
    public ResponseEntity atualizarCarro(@RequestBody AtualizacaoCarroForm carroForm, @RequestHeader(value = "Authorization") String token, @PathVariable String carroId) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        return carroUseCase.atualizarCarro(usuario.getConcessionaria(), carroForm, carroId);
    }

}

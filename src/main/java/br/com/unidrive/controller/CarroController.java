package br.com.unidrive.controller;

import br.com.unidrive.controller.form.AtualizacaoCarroForm;
import br.com.unidrive.controller.form.CarroForm;
import br.com.unidrive.model.Carro;
import br.com.unidrive.useCase.CarroUseCase;
import br.com.unidrive.useCase.UsuarioUseCase;
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
    UsuarioUseCase usuarioUseCase;

    @PostMapping
    public ResponseEntity cadastrarCarro(@RequestBody @Valid List<CarroForm> carroForm, @RequestHeader(value = "Authorization") String token) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        return carroUseCase.cadastrarCarros(carroForm, usuario);

    }

    @DeleteMapping
    public ResponseEntity deletarCarro(@RequestBody List<Carro> carros, @RequestHeader(value = "Authorization") String token) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        var response = carroUseCase.deletarCarros(carros, usuario);

        if (response != null) return ResponseEntity.ok(response);
        else return ResponseEntity.badRequest().build();

    }

    @GetMapping("/{carroId}")
    public ResponseEntity obterCarro(@PathVariable String carroId) {

        return carroUseCase.obterCarroPorId(carroId);
    }


    @GetMapping
    public List<Carro> obterTodosCarros() {
        return carroUseCase.obterTodosCarros();
    }

    @GetMapping("/{modelo}")
    public List<Carro> obterCarrosPorModelo(@PathVariable String modelo) {
        return carroUseCase.obterCarrosPorModelo(modelo);
    }

    @PutMapping("/{carroId}")
    public ResponseEntity atualizarCarro(@RequestBody AtualizacaoCarroForm carroForm, @RequestHeader(value = "Authorization") String token, @PathVariable String carroId) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        return carroUseCase.atualizarCarro(usuario.getConcessionaria(), carroForm, carroId);
    }

}

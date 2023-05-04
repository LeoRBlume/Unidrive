package br.com.unidrive.application.controller;

import br.com.unidrive.application.controller.form.AtualizacaoCarroForm;
import br.com.unidrive.application.controller.form.CarroForm;
import br.com.unidrive.domain.contract.controller.CarroController;
import br.com.unidrive.domain.model.Carro;
import br.com.unidrive.application.useCase.CarroUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroControllerImpl implements CarroController {

    @Autowired
    CarroUseCase carroUseCase;

    @PostMapping
    public ResponseEntity cadastrarCarro(@RequestBody @Valid List<CarroForm> carroForm, @RequestHeader(value = "Authorization") String token) {

        return carroUseCase.cadastrarCarros(token, carroForm);

    }

    @DeleteMapping("/{carroId}")
    public ResponseEntity deletarCarro(@RequestHeader(value = "Authorization") String token, @PathVariable String carroId) {

        return carroUseCase.deletarCarros(token, carroId);

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

        return carroUseCase.atualizarCarro(token, carroForm, carroId);
    }

}

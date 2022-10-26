package br.com.unidrive.controller;

import br.com.unidrive.controller.form.CarroForm;
import br.com.unidrive.useCase.CarroUseCase;
import br.com.unidrive.useCase.UsuarioUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarroController.class);

    @Autowired
    CarroUseCase useCase;

    @Autowired
    UsuarioUseCase usuarioUseCase;

    @PostMapping
    public ResponseEntity cadastrarCarro(@RequestBody @Valid List<CarroForm> carroForm, @RequestHeader(value = "Authorization") String token) {

        LOGGER.info("Obtendo usuario...");
        var usuario = usuarioUseCase.obterUsuarioPorToken(token);
        LOGGER.info("Usuario obetido!");

        return useCase.cadastrarCarros(carroForm, usuario);
    }

}

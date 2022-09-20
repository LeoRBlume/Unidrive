package br.com.unidrive.controller;

import br.com.unidrive.controller.form.CarroForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carro")
public class CarroController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcessionariaController.class);

    @PostMapping
    public ResponseEntity<String> cadastrarCarro(@RequestBody CarroForm carroForm) {

        return null;
    }

}

package br.com.unidrive.controller;

import br.com.unidrive.controller.form.CadastrarConcessionariaForm;
import br.com.unidrive.useCase.ConcessionariaUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/concessionaria")
public class ConcessionariaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcessionariaController.class);

    @Autowired
    ConcessionariaUseCase useCase;

    @PostMapping
    public ResponseEntity<String> cadastrarConcessionaria(@Valid @RequestBody CadastrarConcessionariaForm cadastrarConcessionariaForm) {
        LOGGER.info("Metodo para cadastrar concessionaria...");
        var response = useCase.cadastrarConcessionaria(cadastrarConcessionariaForm);
        LOGGER.info("Cadastro da concessionaria efetuado!");
        return ResponseEntity.status(response.getStatus().value()).body(response.getResposta());
    }
}

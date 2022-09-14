package br.com.unidrive.useCase;

import br.com.unidrive.controller.dto.CadastroConcessionariaDto;
import br.com.unidrive.controller.form.CadastrarConcessionariaForm;
import br.com.unidrive.model.Concessionaria;
import br.com.unidrive.repository.ConcessionariaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class ConcessionariaUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcessionariaUseCase.class);

    @Autowired
    ConcessionariaRepository concessionariaRepository;


    @PostMapping
    @RequestMapping("/cadastrar")
    public CadastroConcessionariaDto cadastrarConcessionaria(CadastrarConcessionariaForm cadastrarConcessionariaForm) {
        LOGGER.info("Iniciando metodo para cadastrar concessionaria...");

        if (concessionariaRepository.findById(cadastrarConcessionariaForm.getCnpj()).isPresent()) {
            return new CadastroConcessionariaDto(HttpStatus.CONFLICT, "CNPJ ja cadastrado");
        }

        var time = Timestamp.valueOf(LocalDateTime.now());

        LOGGER.info("Criando concessionaria apartir do formulario...");

        var concessionaria = new Concessionaria(
                cadastrarConcessionariaForm.getCnpj(),
                cadastrarConcessionariaForm.getNome(),
                time);

        LOGGER.info("Concessionaria criada!");

        concessionariaRepository.save(concessionaria);

        LOGGER.info("Concessionaria cadastrada no banco!");
        return new CadastroConcessionariaDto(HttpStatus.CREATED, "Cadastro realizado com sucesso!");
    }

}
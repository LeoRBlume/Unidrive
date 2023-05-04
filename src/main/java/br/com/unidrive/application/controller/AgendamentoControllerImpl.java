package br.com.unidrive.application.controller;

import br.com.unidrive.application.controller.form.AgendamentoForm;
import br.com.unidrive.application.useCase.AgendamentoUseCaseImpl;
import br.com.unidrive.domain.contract.controller.AgendamentoController;
import br.com.unidrive.domain.model.Agendamento;
import br.com.unidrive.domain.model.Carro;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoControllerImpl implements AgendamentoController {

    @Autowired
    AgendamentoUseCaseImpl agendamentoUseCaseImpl;


    @PostMapping
    public ResponseEntity cadastrarAgendamento(@RequestHeader(value = "Authorization") String token, @RequestBody @Valid AgendamentoForm agendamentoForm) {


        return agendamentoUseCaseImpl.cadastrarAgendamento(token, agendamentoForm);

    }

    @JsonSerialize
    @GetMapping("/usuario")
    public List<Agendamento> obterAgendamentosPorUsuario(@RequestHeader(value = "Authorization") String token) {

        return agendamentoUseCaseImpl.obterAgendamentosPorUsuarioToken(token);

    }

    @PutMapping("/carro")
    public List<Agendamento> obterAgendamentosPorCarro(@RequestBody Carro carro) {

        return agendamentoUseCaseImpl.obterAgendamentosPorCarro(carro);

    }

    @GetMapping("/concessionaria")
    public List<Agendamento> obterAgendamentoPorConcessionaria(@RequestHeader(value = "Authorization") String token) {

        return agendamentoUseCaseImpl.obterAgendamentosPorConcessionariaToken(token);
    }

    @DeleteMapping("/deletar/{agendamentoId}")
    public ResponseEntity deletarAgendamento(@RequestHeader(value = "Authorization") String token, @PathVariable String agendamentoId) {

        return agendamentoUseCaseImpl.deletarAgendamentoToken(token, agendamentoId);

    }

}

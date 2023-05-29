package br.com.unidrive.domain.contract.controller;

import br.com.unidrive.application.controller.form.AgendamentoForm;
import br.com.unidrive.domain.model.Agendamento;
import br.com.unidrive.domain.model.Carro;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AgendamentoController {

    ResponseEntity cadastrarAgendamento(String token, AgendamentoForm agendamentoForm);

    List<Agendamento> obterAgendamentosPorUsuario(String token);

    List<Agendamento> obterAgendamentosPorCarro(Carro carro);

    List<Agendamento> obterAgendamentoPorConcessionaria(String token);

    ResponseEntity deletarAgendamento(String token, String agendamentoId);


}
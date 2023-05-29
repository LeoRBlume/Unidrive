package br.com.unidrive.domain.contract.useCase;

import br.com.unidrive.application.controller.form.AgendamentoForm;
import br.com.unidrive.domain.model.Agendamento;
import br.com.unidrive.domain.model.Carro;
import br.com.unidrive.domain.model.Concessionaria;
import br.com.unidrive.domain.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AgendamentoUseCase {

        public ResponseEntity cadastrarAgendamento(String token, AgendamentoForm agendamentoForm);

        public List<Agendamento> obterAgendamentosPorUsuarioToken(String token);

        public List<Agendamento> obterAgendamentosPorConcessionariaToken(String token);

        public List<Agendamento> obterAgendamentosPorCarro(Carro carro);

        public ResponseEntity deletarAgendamentoToken(String token, String agendamentoId);


}

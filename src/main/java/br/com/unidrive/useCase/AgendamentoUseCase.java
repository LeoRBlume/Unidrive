package br.com.unidrive.useCase;

import br.com.unidrive.controller.dto.AgendamentoDto;
import br.com.unidrive.controller.form.AgendamentoForm;
import br.com.unidrive.model.Agendamento;
import br.com.unidrive.model.Carro;
import br.com.unidrive.model.Concessionaria;
import br.com.unidrive.model.Usuario;
import br.com.unidrive.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AgendamentoUseCase {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    CarroUseCase carroUseCase;

    public ResponseEntity cadastrarAgendamento(Usuario usuario, AgendamentoForm agendamentoForm) {

        try {
            var agendamento = new Agendamento(usuario, agendamentoForm);

            agendamentoRepository.save(agendamento);

            return ResponseEntity.ok(agendamento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    public List<AgendamentoDto> obterAgendamentosPorUsuarioDto(Usuario usuario) {

        var agendamentos = obterAgendamentosPorUsuario(usuario);

        for (Agendamento a : agendamentos) System.out.println(a);

        if (!agendamentos.isEmpty()) {

            return AgendamentoDto.transformarListaAgendamento(agendamentos);
        } else
            return null;

    }

    public List<Agendamento> obterAgendamentosPorUsuario(Usuario usuario) {

        return agendamentoRepository.findAllByUsuario(usuario);

    }

    public List<AgendamentoDto> obterAgendamentosPorConcessionariaDto(Concessionaria concessionaria) {

        var agendamentos = obterAgendamentosPorConcessionaria(concessionaria);

        if (!agendamentos.isEmpty()) {
            return AgendamentoDto.transformarListaAgendamento(agendamentos);
        } else
            return null;

    }

    public List<Agendamento> obterAgendamentosPorConcessionaria(Concessionaria concessionaria) {
        if (concessionaria != null) {
            var listaCarros = carroUseCase.obterCarrosPorConcessionaria(concessionaria);

            var listaAgendamentos = new ArrayList<Agendamento>();

            for (Carro c : listaCarros) {
                listaAgendamentos.addAll(agendamentoRepository.findAllByCarro(c));
            }

            return listaAgendamentos;

        } else {
            return null;
        }
    }


    public List<AgendamentoDto> obterAgendamentosPorCarro(Carro carro) {

        return AgendamentoDto.transformarListaAgendamento(agendamentoRepository.findAllByCarro(carro));

    }

    public ResponseEntity deletarAgendamento(Usuario usuario, Concessionaria concessionaria, String agendamentoId) {

        var agendamentoOp = agendamentoRepository.findById(Long.parseLong(agendamentoId));

        if (!agendamentoOp.isPresent()) {

            return ResponseEntity.badRequest().build();
        } else {
            var agendamento = agendamentoOp.get();
            if (agendamento.getUsuario().equals(usuario) || agendamento.getCarro().getConcessionaria().equals(concessionaria)) {
                agendamentoRepository.delete(agendamento);
                return ResponseEntity.ok().build();
            } else
                return ResponseEntity.badRequest().build();
        }

    }
}

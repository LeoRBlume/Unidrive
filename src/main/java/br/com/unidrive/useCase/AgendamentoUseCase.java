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
            agendamentoRepository.save(new Agendamento(usuario, agendamentoForm));

            return ResponseEntity.ok().build();

        } catch (Exception e) {

            return ResponseEntity.badRequest().build();
        }

    }

    public List<AgendamentoDto> obterAgendamentosPorUsuario(Usuario usuario) {

        return AgendamentoDto.transformarListaAgendamento(agendamentoRepository.findAllByUsuario(usuario));

    }


    public List<AgendamentoDto> obterAgendamentosPorConcessionaria(Concessionaria concessionaria) {

        if (concessionaria != null) {
            var listaCarros = carroUseCase.obterCarrosPorConcessionaria(concessionaria);

            var listaAgendamentos = new ArrayList<AgendamentoDto>();

            for (Carro c : listaCarros) {
                listaAgendamentos.addAll(AgendamentoDto.transformarListaAgendamento(agendamentoRepository.findAllByCarro(c)));
            }

            return listaAgendamentos;

        } else {
            return null;
        }

    }

    public List<AgendamentoDto> obterAgendamentosPorCarro(Carro carro) {

        return AgendamentoDto.transformarListaAgendamento(agendamentoRepository.findAllByCarro(carro));

    }
}

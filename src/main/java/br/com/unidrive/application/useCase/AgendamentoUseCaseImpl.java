package br.com.unidrive.application.useCase;

import br.com.unidrive.application.controller.form.AgendamentoForm;
import br.com.unidrive.domain.contract.useCase.AgendamentoUseCase;
import br.com.unidrive.domain.model.Agendamento;
import br.com.unidrive.domain.model.Carro;
import br.com.unidrive.domain.model.Concessionaria;
import br.com.unidrive.domain.model.Usuario;
import br.com.unidrive.infrastructure.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoUseCaseImpl implements AgendamentoUseCase {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    UsuarioUseCaseImpl usuarioUseCase;

    @Autowired
    ConcessionariaUseCaseImpl concessionariaUseCaseImpl;

    @Autowired
    CarroUseCaseImpl carroUseCaseImpl;

    public ResponseEntity cadastrarAgendamento(String token, AgendamentoForm agendamentoForm) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        try {
            var agendamento = new Agendamento(usuario, agendamentoForm);

            agendamentoRepository.save(agendamento);

            return ResponseEntity.ok(agendamento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public List<Agendamento> obterAgendamentosPorUsuarioToken(String token) {

        return obterAgendamentosPorUsuario(usuarioUseCase.obterUsuarioPorToken(token));


    }


    public List<Agendamento> obterAgendamentosPorUsuario(Usuario usuario) {

        return agendamentoRepository.findAllByUsuario(usuario);

    }


    public List<Agendamento> obterAgendamentosPorConcessionariaToken(String token) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        return obterAgendamentosPorConcessionaria(concessionariaUseCaseImpl.obterConcessionaria(usuario));
    }
    public List<Agendamento> obterAgendamentosPorConcessionaria(Concessionaria concessionaria) {


        if (concessionaria != null) {
            var listaCarros = carroUseCaseImpl.obterCarrosPorConcessionaria(concessionaria);

            var listaAgendamentos = new ArrayList<Agendamento>();

            for (Carro c : listaCarros) {
                listaAgendamentos.addAll(agendamentoRepository.findAllByCarro(c));
            }

            return listaAgendamentos;

        } else {
            return null;
        }
    }


    public List<Agendamento> obterAgendamentosPorCarro(Carro carro) {

        return agendamentoRepository.findAllByCarro(carro);

    }

    public ResponseEntity deletarAgendamentoToken(String token, String agendamentoId) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);


        return deletarAgendamento(usuario, concessionariaUseCaseImpl.obterConcessionaria(usuario), agendamentoId);

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

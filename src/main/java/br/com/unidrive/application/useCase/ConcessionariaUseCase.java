package br.com.unidrive.application.useCase;

import br.com.unidrive.application.controller.form.AtualizacaoConcessionariaForm;
import br.com.unidrive.application.controller.form.CadastrarConcessionariaForm;
import br.com.unidrive.domain.model.Agendamento;
import br.com.unidrive.domain.model.Carro;
import br.com.unidrive.domain.model.Concessionaria;
import br.com.unidrive.domain.model.Usuario;
import br.com.unidrive.infrastructure.repository.ConcessionariaRepository;
import br.com.unidrive.infrastructure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConcessionariaUseCase {

    @Autowired
    ConcessionariaRepository concessionariaRepository;

    @Autowired
    EnderecoUseCase enderecoUseCase;
    @Autowired
    CarroUseCase carroUseCase;

    @Autowired
    AgendamentoUseCaseImpl agendamentoUseCaseImpl;

    @Autowired
    UsuarioRepository usuarioRepository;


    public Concessionaria cadastrarConcessionaria(CadastrarConcessionariaForm cadastrarConcessionariaForm) {

        if (concessionariaRepository.findByCnpj(cadastrarConcessionariaForm.getCnpj()).isPresent()) {
            return null;
        }

        var time = Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime().toString();

        var endereco = enderecoUseCase.cadastrarEndereco(cadastrarConcessionariaForm.getEnderecoForm());

        var concessionaria = new Concessionaria(
                cadastrarConcessionariaForm,
                time,
                endereco
        );

        concessionariaRepository.save(concessionaria);

        return concessionaria;
    }

    public Concessionaria obterConcessionaria(Usuario usuario) {

        try {
            return usuario.getConcessionaria();

        } catch (Exception e) {
            return null;
        }

    }

    public ResponseEntity<?> atualizarConcessionaria(Usuario usuario, AtualizacaoConcessionariaForm atualizacaoConcessionariaForm) {

        var concessionaria = obterConcessionaria(usuario);

        if (concessionaria != null) {

            concessionaria.setNomeFantasia(atualizacaoConcessionariaForm.getNome());
            concessionariaRepository.save(concessionaria);

            return ResponseEntity.ok(concessionaria);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    public List<Carro> obterCarrosConcessionaria(Usuario usuario) {

        var concessionaria = obterConcessionaria(usuario);

        if (concessionaria != null) {

            return carroUseCase.obterCarrosPorConcessionaria(concessionaria);

        }
        return new ArrayList<>();
    }

    public ResponseEntity deletarConcessionaria(Usuario usuario) {

        if (usuario.getConcessionaria() == null) {
            return ResponseEntity.badRequest().build();
        } else {

            var concessionaria = usuario.getConcessionaria();

            var agendamentos = agendamentoUseCaseImpl.obterAgendamentosPorConcessionaria(concessionaria);

            for (Agendamento a : agendamentos) {
                agendamentoUseCaseImpl.deletarAgendamento(null, concessionaria, a.getId().toString());
            }

            usuarioRepository.delete(usuario);

            concessionariaRepository.delete(concessionaria);

            return ResponseEntity.ok().build();
        }

    }
}

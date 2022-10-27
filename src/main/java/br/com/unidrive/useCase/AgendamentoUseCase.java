package br.com.unidrive.useCase;

import br.com.unidrive.controller.form.AgendamentoForm;
import br.com.unidrive.model.Agendamento;
import br.com.unidrive.model.Usuario;
import br.com.unidrive.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoUseCase {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    public ResponseEntity cadastrarAgendamento(Usuario usuario, AgendamentoForm agendamentoForm) {

        try {
            agendamentoRepository.save(new Agendamento(usuario, agendamentoForm));

            return ResponseEntity.ok().build();
            
        } catch (Exception e) {

            return ResponseEntity.badRequest().build();
        }


    }
}

package br.com.unidrive.repository;

import br.com.unidrive.model.Agendamento;
import br.com.unidrive.model.Carro;
import br.com.unidrive.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findAllByUsuario(Usuario usuario);

    List<Agendamento> findAllByCarro(Carro carro);

}

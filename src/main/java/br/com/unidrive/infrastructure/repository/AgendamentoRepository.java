package br.com.unidrive.infrastructure.repository;

import br.com.unidrive.domain.model.Agendamento;
import br.com.unidrive.domain.model.Carro;
import br.com.unidrive.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findAllByUsuario(Usuario usuario);

    List<Agendamento> findAllByCarro(Carro carro);

    @Query("SELECT a FROM Agendamento a WHERE a.carro = ?1 and a.dt_agendamento= ?2 and a.hr_final > ?4 and a.hr_inicial < ?3 or a.hr_final > ?3")
    Collection<Agendamento> obterAgendamentoHorario(Long id, String dt_agendamento, String hr_inicial, String hr_final);
}

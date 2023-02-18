package br.com.unidrive.infrastructure.repository;

import br.com.unidrive.domain.Carro;
import br.com.unidrive.domain.Concessionaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findAllByConcessionaria(Concessionaria concessionaria);

}

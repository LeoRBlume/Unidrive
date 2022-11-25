package br.com.unidrive.repository;

import br.com.unidrive.model.Carro;
import br.com.unidrive.model.Concessionaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findAllByConcessionaria(Concessionaria concessionaria);

}

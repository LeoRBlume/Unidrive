package br.com.unidrive.repository;

import br.com.unidrive.model.Concessionaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConcessionariaRepository extends JpaRepository<Concessionaria, Long> {

    Optional<Concessionaria> findByCnpj(String cnpj);


}

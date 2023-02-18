package br.com.unidrive.infrastructure.repository;

import br.com.unidrive.domain.Concessionaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConcessionariaRepository extends JpaRepository<Concessionaria, Long> {

    Optional<Concessionaria> findByCnpj(String cnpj);


}

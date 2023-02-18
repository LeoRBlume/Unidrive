package br.com.unidrive.infrastructure.repository;

import br.com.unidrive.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

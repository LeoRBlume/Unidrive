package br.com.unidrive.infrastructure.repository;

import br.com.unidrive.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByConcessionaria_Cnpj(String cnpj);



}

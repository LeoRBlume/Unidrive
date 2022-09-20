package br.com.unidrive.repository;

import br.com.unidrive.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByConcessionaria_Cnpj(String cnpj);



}

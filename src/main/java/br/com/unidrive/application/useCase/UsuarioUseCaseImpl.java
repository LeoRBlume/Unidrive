package br.com.unidrive.application.useCase;


import br.com.unidrive.application.controller.dto.ResponseDto;
import br.com.unidrive.application.controller.dto.UsuarioDto;
import br.com.unidrive.application.controller.form.UsuarioForm;
import br.com.unidrive.domain.model.Concessionaria;
import br.com.unidrive.domain.model.Usuario;
import br.com.unidrive.domain.contract.useCase.UsuarioUseCase;
import br.com.unidrive.domain.model.Agendamento;
import br.com.unidrive.infrastructure.config.security.TokenService;
import br.com.unidrive.infrastructure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import java.util.Optional;


@Component
public class UsuarioUseCaseImpl implements UsuarioUseCase {


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    EnderecoUseCaseImpl enderecoUseCaseImpl;

    @Autowired
    AgendamentoUseCaseImpl agendamentoUseCaseImpl;


    public ResponseEntity<?> obterUsuario(String token) {
        Usuario usuario = usuarioToken(token);
        UsuarioDto usuarioDto = UsuarioDto.converter(usuario);
        return ResponseEntity.ok(usuarioDto);
    }

    public ResponseEntity<String> cadastrarUsuario(UsuarioForm usuarioForm) {
        try {
            if (!validandoEmail(usuarioForm.getEmail()))
                return new ResponseEntity<>("Formato do email invalido", HttpStatus.BAD_REQUEST);

            var usuario = transformarUsuarioForm(usuarioForm);

            usuarioRepository.save(usuario);

            return new ResponseEntity<>("Usuario cadastrado", HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Email ja cadastrado!!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Algum erro inesperado aconteceu", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deletarUsuario(String token) {

        var usuario = obterUsuarioPorToken(token);

        if (usuario.getConcessionaria() != null) {
            return ResponseEntity.badRequest().build();
        } else {

            var agendamentos = agendamentoUseCaseImpl.obterAgendamentosPorUsuario(usuario);

            for (Agendamento a : agendamentos) {
                agendamentoUseCaseImpl.deletarAgendamento(usuario, null, a.getId().toString());
            }

        }

        usuarioRepository.delete(usuario);

        return ResponseEntity.ok().build();

    }

    public ResponseEntity<?> obterUsuarioPorEmail(String email) {
        if (!validandoEmail(email)) return new ResponseEntity<>("Formato de email invalido!!", HttpStatus.BAD_REQUEST);

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            return new ResponseEntity<>(usuarioOptional.get(), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Long obterIdUsuario(String tokenBearer) {
        String token = tokenService.recuperarToken(tokenBearer);
        return tokenService.getIdUsuario(token);
    }

    private Usuario usuarioToken(String token) {
        token = tokenService.recuperarToken(token);
        return usuarioRepository.getById(tokenService.getIdUsuario(token));
    }

    public Usuario obterUsuarioPorToken(String token) {
        return usuarioToken(token);
    }

    public ResponseDto vincularConcessionaria(Usuario usuario, Concessionaria concessionariaCadastrada) {
        try {
            usuario.setConcessionaria(concessionariaCadastrada);
            usuarioRepository.save(usuario);
            return new ResponseDto(HttpStatus.ACCEPTED, "Concessionaria vinculada ao usuario");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private boolean validandoEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    private Usuario transformarUsuarioForm(UsuarioForm usuarioForm) throws Exception {

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioForm.getNome());

        usuario.setEmail(usuarioForm.getEmail().toLowerCase());

        usuario.setSenha(passwordEncoder.encode(usuarioForm.getSenha()));

        usuario.setCnh(usuarioForm.getCnh());

        usuario.setCpf(usuarioForm.getCpf());

        usuario.setTelefone(usuarioForm.getTelefone());

        usuario.setEndereco(enderecoUseCaseImpl.cadastrarEndereco(usuarioForm.getEndereco()));

        return usuario;

    }
}

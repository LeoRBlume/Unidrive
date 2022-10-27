package br.com.unidrive.useCase;


import br.com.unidrive.config.security.TokenService;
import br.com.unidrive.controller.dto.ResponseDto;
import br.com.unidrive.controller.dto.UsuarioDto;
import br.com.unidrive.controller.form.AtualizacaoUsuarioForm;
import br.com.unidrive.controller.form.UsuarioForm;
import br.com.unidrive.model.Carro;
import br.com.unidrive.model.Concessionaria;
import br.com.unidrive.model.Usuario;
import br.com.unidrive.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.Optional;


@Component
public class UsuarioUseCase {


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    public ResponseEntity<String> cadastrarUsuario(UsuarioForm usuarioForm) {


        try {

            Usuario usuario = new Usuario();
            usuario.setNome(usuarioForm.getNome());

            if (!validandoEmail(usuarioForm.getEmail()))
                return new ResponseEntity<>("Formato do email invalido", HttpStatus.BAD_REQUEST);

            usuario.setEmail(usuarioForm.getEmail().toLowerCase());

            usuario.setSenha(passwordEncoder.encode(usuarioForm.getSenha()));

            usuarioRepository.save(usuario);


            return new ResponseEntity<>("Usuario cadastrado com sucesso!", HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Email ja cadastrado!!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Algum erro inesperado aconteceu", HttpStatus.BAD_REQUEST);
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

    public ResponseEntity<?> obterUsuarioPorEmail(String email) {
        if (!validandoEmail(email)) return new ResponseEntity<>("Formato de email invalido!!", HttpStatus.BAD_REQUEST);

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            return new ResponseEntity<>(usuarioOptional.get(), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    private Usuario obterUsuarioToken(String tokenBearer) {
        return usuarioRepository.getById(obterIdUsuario(tokenBearer));
    }

    private Long obterIdUsuario(String tokenBearer) {
        String token = tokenService.recuperarToken(tokenBearer);
        return tokenService.getIdUsuario(token);
    }

    public ResponseEntity<UsuarioDto> atualizarCadastro(String tokenBearer, AtualizacaoUsuarioForm form) {
        Usuario usuario = usuarioRepository.getById(obterIdUsuario(tokenBearer));
        UsuarioDto usuarioDto = new UsuarioDto();

        if (form.getNome() != null) {
            usuario.setNome(form.getNome());
        }
        if (form.getEmail() != null) {
            usuario.setEmail(form.getEmail().toLowerCase());
        }
        if (form.getSenha() != null) {
            usuario.setSenha(passwordEncoder.encode(form.getSenha()));
            usuarioDto.setSenha("Senha alterada com sucesso!");
        }

        usuarioRepository.save(usuario);

        usuarioDto.converterAtualizacoes(usuario);

        return ResponseEntity.ok(usuarioDto);
    }

    public ResponseEntity<UsuarioDto> obterUsuario(String token) {
        Usuario usuario = usuarioToken(token);
        UsuarioDto usuarioDto = UsuarioDto.converter(usuario);
        return ResponseEntity.ok(usuarioDto);
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
}

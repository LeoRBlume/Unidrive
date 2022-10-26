package br.com.unidrive.controller;

import br.com.unidrive.config.security.TokenService;
import br.com.unidrive.controller.dto.TokenDto;
import br.com.unidrive.controller.form.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping
    public ResponseEntity<TokenDto> autenticarLogin(@RequestBody LoginForm loginForm) {

        LOGGER.info("Metodo para realizar o login...");

        UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            var response =  ResponseEntity.ok(new TokenDto(token, "Bearer"));
            LOGGER.info("Retornando o token de autenticação!");
            return response;
        }
        catch (AuthenticationException e){
            LOGGER.error("Usuario ou senha incorretos!!");
            return ResponseEntity.badRequest().build();
        }
    }
}

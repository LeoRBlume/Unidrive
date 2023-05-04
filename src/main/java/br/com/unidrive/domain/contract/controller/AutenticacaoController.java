package br.com.unidrive.domain.contract.controller;

import br.com.unidrive.application.controller.dto.TokenDto;
import br.com.unidrive.application.controller.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AutenticacaoController {

    public ResponseEntity<TokenDto> autenticarLogin(@RequestBody LoginForm loginForm);

}

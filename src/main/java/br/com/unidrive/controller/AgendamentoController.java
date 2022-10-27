package br.com.unidrive.controller;

import br.com.unidrive.controller.form.AgendamentoForm;
import br.com.unidrive.useCase.AgendamentoUseCase;
import br.com.unidrive.useCase.UsuarioUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    AgendamentoUseCase agendamentoUseCase;

    @Autowired
    UsuarioUseCase usuarioUseCase;

    @PostMapping
    public ResponseEntity cadastrarAgendamento(@RequestHeader(value = "Authorization") String token, @RequestBody @Valid AgendamentoForm agendamentoForm) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        return agendamentoUseCase.cadastrarAgendamento(usuario, agendamentoForm);

    }
}

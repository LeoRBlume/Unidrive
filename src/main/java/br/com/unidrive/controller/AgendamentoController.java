package br.com.unidrive.controller;

import br.com.unidrive.controller.dto.AgendamentoDto;
import br.com.unidrive.controller.form.AgendamentoForm;
import br.com.unidrive.model.Carro;
import br.com.unidrive.useCase.AgendamentoUseCase;
import br.com.unidrive.useCase.ConcessionariaUseCase;
import br.com.unidrive.useCase.UsuarioUseCase;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    AgendamentoUseCase agendamentoUseCase;

    @Autowired
    UsuarioUseCase usuarioUseCase;

    @Autowired
    ConcessionariaUseCase concessionariaUseCase;

    @PostMapping
    public ResponseEntity cadastrarAgendamento(@RequestHeader(value = "Authorization") String token, @RequestBody @Valid AgendamentoForm agendamentoForm) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        return agendamentoUseCase.cadastrarAgendamento(usuario, agendamentoForm);

    }

    @JsonSerialize
    @GetMapping("/usuario")
    public List<AgendamentoDto> obterAgendamentosPorUsuario(@RequestHeader(value = "Authorization") String token) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        return agendamentoUseCase.obterAgendamentosPorUsuarioDto(usuario);

    }

    @PutMapping("/carro")
    public List<AgendamentoDto> obterAgendamentosPorCarro(@RequestBody Carro carro) {

        return agendamentoUseCase.obterAgendamentosPorCarro(carro);

    }

    @GetMapping("/concessionaria")
    public List<AgendamentoDto> obterAgendamentoPorConcessionaria(@RequestHeader(value = "Authorization") String token) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        var concessionaria = concessionariaUseCase.obterConcessionaria(usuario);

        return agendamentoUseCase.obterAgendamentosPorConcessionariaDto(concessionaria);
    }

    @DeleteMapping("/deletar/{agendamentoId}")
    public ResponseEntity deletarAgendamento(@RequestHeader(value = "Authorization") String token, @PathVariable String agendamentoId){
        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        var concessionaria = concessionariaUseCase.obterConcessionaria(usuario);

        return agendamentoUseCase.deletarAgendamento(usuario, concessionaria, agendamentoId);

    }

}

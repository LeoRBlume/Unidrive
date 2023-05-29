package br.com.unidrive.domain.contract.controller;

import br.com.unidrive.application.controller.form.AtualizacaoConcessionariaForm;
import br.com.unidrive.application.controller.form.CadastrarConcessionariaForm;
import br.com.unidrive.domain.model.Carro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.util.List;

public interface ConcessionariaController {

    public ResponseEntity<?> obterConcessionaria(@RequestHeader(value = "Authorization") String token);

    public ResponseEntity<String> cadastrarConcessionaria(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody CadastrarConcessionariaForm cadastrarConcessionariaForm);

    public ResponseEntity<?> atualizarConcessionaria(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody AtualizacaoConcessionariaForm atualizacaoConcessionariaForm);

    public List<Carro> obterCarrosConcessionaria(@RequestHeader(value = "Authorization") String token);

    public ResponseEntity deletarConcessionaria(@RequestHeader(value = "Authorization") String token);


}

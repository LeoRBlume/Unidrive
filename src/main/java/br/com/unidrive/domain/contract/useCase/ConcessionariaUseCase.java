package br.com.unidrive.domain.contract.useCase;

import br.com.unidrive.application.controller.form.AtualizacaoConcessionariaForm;
import br.com.unidrive.application.controller.form.CadastrarConcessionariaForm;
import br.com.unidrive.domain.model.Carro;
import br.com.unidrive.domain.model.Concessionaria;
import br.com.unidrive.domain.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConcessionariaUseCase {

    Concessionaria cadastrarConcessionaria(CadastrarConcessionariaForm cadastrarConcessionariaForm);

    Concessionaria obterConcessionaria(Usuario usuario);

    ResponseEntity<?> atualizarConcessionaria(Usuario usuario, AtualizacaoConcessionariaForm atualizacaoConcessionariaForm);

    List<Carro> obterCarrosConcessionaria(Usuario usuario);

    ResponseEntity deletarConcessionaria(Usuario usuario);

}

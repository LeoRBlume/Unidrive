package br.com.unidrive.useCase;

import br.com.unidrive.controller.form.AtualizacaoConcessionariaForm;
import br.com.unidrive.controller.form.CadastrarConcessionariaForm;
import br.com.unidrive.model.Carro;
import br.com.unidrive.model.Concessionaria;
import br.com.unidrive.model.Usuario;
import br.com.unidrive.repository.ConcessionariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConcessionariaUseCase {

    @Autowired
    ConcessionariaRepository concessionariaRepository;

    @Autowired
    CarroUseCase carroUseCase;


    public Concessionaria cadastrarConcessionaria(CadastrarConcessionariaForm cadastrarConcessionariaForm) {

        if (concessionariaRepository.findByCnpj(cadastrarConcessionariaForm.getCnpj()).isPresent()) {
            return null;
        }

        var time = Timestamp.valueOf(LocalDateTime.now());

        var concessionaria = new Concessionaria(
                cadastrarConcessionariaForm.getCnpj(),
                cadastrarConcessionariaForm.getNome(),
                time);

        concessionariaRepository.save(concessionaria);

        return concessionaria;
    }

    public Concessionaria obterConcessionaria(Usuario usuario) {

        try {
            return usuario.getConcessionaria();

        } catch (Exception e) {
            return null;
        }

    }

    public ResponseEntity<?> atualizarConcessionaria(Usuario usuario, AtualizacaoConcessionariaForm atualizacaoConcessionariaForm) {

        var concessionaria = obterConcessionaria(usuario);

        if (concessionaria != null) {

            concessionaria.setNome(atualizacaoConcessionariaForm.getNome());
            concessionariaRepository.save(concessionaria);

            return ResponseEntity.ok(concessionaria);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    public List<Carro> obterCarrosConcessionaria(Usuario usuario) {

        var concessionaria = obterConcessionaria(usuario);

        if (concessionaria != null) {

            return carroUseCase.obterCarrosPorConcessionaria(concessionaria);

        }
        return new ArrayList<>();
    }
}

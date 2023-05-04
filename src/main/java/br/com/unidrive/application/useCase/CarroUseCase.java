package br.com.unidrive.application.useCase;

import br.com.unidrive.application.controller.form.AtualizacaoCarroForm;
import br.com.unidrive.application.controller.form.CarroForm;
import br.com.unidrive.domain.model.Carro;
import br.com.unidrive.domain.model.Concessionaria;
import br.com.unidrive.domain.model.Usuario;
import br.com.unidrive.infrastructure.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class CarroUseCase {

    @Autowired
    CarroRepository carroRepository;

    @Autowired
    UsuarioUseCaseImpl usuarioUseCase;

    @Autowired
    ConcessionariaUseCase concessionariaUseCase;


    public ResponseEntity cadastrarCarros(String token, List<CarroForm> carroFormList) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        if (carroFormList.isEmpty()) return ResponseEntity.noContent().build();

        if (usuario.getConcessionaria() == null) {
            return ResponseEntity.badRequest().build();
        }

        var time = Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime().toString();

        for (CarroForm c : carroFormList) {
            var carro = Carro.cadastroCarroForm(c, time, usuario.getConcessionaria());
            carroRepository.save(carro);
        }

        return ResponseEntity.ok().build();
    }

    public List<Carro> obterCarrosPorConcessionaria(Concessionaria concessionaria) {

        return carroRepository.findAllByConcessionaria(concessionaria);

    }

    public ResponseEntity deletarCarros(String token, String carroId) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        var concessionaria = concessionariaUseCase.obterConcessionaria(usuario);

        var carroOp = carroRepository.findById(Long.parseLong(carroId));

        if (carroOp.isPresent()) {

            var carro = carroOp.get();

            if (carro.getConcessionaria().equals(concessionaria)) {
                var response = ResponseEntity.ok(carro);
                carroRepository.delete(carro);
                return response;
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    public List<Carro> obterTodosCarros() {

        return carroRepository.findAll();

    }

    public ResponseEntity atualizarCarro(String token, AtualizacaoCarroForm carroForm, String carroId) {

        var usuario = usuarioUseCase.obterUsuarioPorToken(token);

        Concessionaria concessionaria = usuario.getConcessionaria();

        var carroOp = carroRepository.findById(Long.valueOf(carroId));

        if (!carroOp.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            var carro = carroOp.get();
            if (carro.getConcessionaria().equals(concessionaria)) {

                if (!carroForm.quilometragem.isBlank()) {
                    carro.setQuilometragem(carroForm.quilometragem);
                }
                if (!carroForm.cor.isBlank()) {
                    carro.setCor(carroForm.cor);
                }
                if (!carroForm.placa.isBlank()) {
                    carro.setPlaca(carroForm.placa);
                }
                if (!carroForm.valor.isBlank()) {
                    carro.setValor(carroForm.valor);
                }

                carroRepository.save(carro);

                return ResponseEntity.ok(carro);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

    }

    public ResponseEntity obterCarroPorId(String carroId) {

        var carro = carroRepository.findById(Long.parseLong(carroId));

        if (carro.isPresent()) return ResponseEntity.ok(carro);

        else return ResponseEntity.badRequest().build();

    }
}

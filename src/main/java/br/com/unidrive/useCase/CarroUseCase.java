package br.com.unidrive.useCase;

import br.com.unidrive.controller.form.CarroForm;
import br.com.unidrive.model.Carro;
import br.com.unidrive.model.Concessionaria;
import br.com.unidrive.model.Usuario;
import br.com.unidrive.repository.CarroRepository;
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

    public ResponseEntity cadastrarCarros(List<CarroForm> carroFormList, Usuario usuario) {

        if (carroFormList.isEmpty()) return ResponseEntity.noContent().build();

        if (usuario.getConcessionaria() == null) {
            return ResponseEntity.badRequest().build();
        }

        var time = Timestamp.valueOf(LocalDateTime.now());

        for (CarroForm c : carroFormList) {

            var carro = Carro.cadastroCarroForm(c, time, usuario.getConcessionaria());
            carroRepository.save(carro);
        }

        return ResponseEntity.ok().build();
    }

    public List<Carro> obterCarrosPorConcessionaria(Concessionaria concessionaria) {

        return carroRepository.findAllByConcessionaria(concessionaria);

    }
}

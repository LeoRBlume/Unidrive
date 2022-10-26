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
import java.util.ArrayList;
import java.util.List;

@Component
public class CarroUseCase {


    @Autowired
    CarroRepository carroRepository;

    @Autowired
    ConcessionariaUseCase concessionariaUseCase;

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
    public List<Carro> deletarCarros(List<Carro> carros, Usuario usuario) {

        var concessionaria = concessionariaUseCase.obterConcessionaria(usuario);

        var carrosExcluidos = new ArrayList<Carro>();
        if (concessionaria != null) {

            var carrosConcessionaria = obterCarrosPorConcessionaria(concessionaria);

            for (Carro c : carros) {
                System.out.println(c);
                for (Carro c1 : carrosConcessionaria) System.out.println(c1);
                if (carrosConcessionaria.contains(c)) {



                    carroRepository.delete(c);
                    carrosExcluidos.add(c);
                }
            }
            return carrosExcluidos;
        }
        return null;

    }
}

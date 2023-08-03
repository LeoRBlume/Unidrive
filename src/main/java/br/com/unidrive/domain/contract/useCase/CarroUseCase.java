package br.com.unidrive.domain.contract.useCase;

import br.com.unidrive.application.controller.form.AtualizacaoCarroForm;
import br.com.unidrive.application.controller.form.CarroForm;
import br.com.unidrive.domain.model.Carro;
import br.com.unidrive.domain.model.Concessionaria;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarroUseCase {

    ResponseEntity cadastrarCarros(String token, List<CarroForm> carroFormList);

    List<Carro> obterCarrosPorConcessionaria(Concessionaria concessionaria);

    ResponseEntity deletarCarros(String token, String carroId);

    List<Carro> obterTodosCarros();

    ResponseEntity atualizarCarro(String token, AtualizacaoCarroForm carroForm, String carroId);

    ResponseEntity obterCarroPorId(String carroId);
    List<Carro> filtrarCarrosPorMarcaEModelo(String marca, String modelo);
}

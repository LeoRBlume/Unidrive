package br.com.unidrive.domain.contract.controller;

import br.com.unidrive.application.controller.form.AtualizacaoCarroForm;
import br.com.unidrive.application.controller.form.CarroForm;
import br.com.unidrive.domain.model.Carro;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarroController {

    public ResponseEntity cadastrarCarro(List<CarroForm> carroForm, String token);

    public ResponseEntity deletarCarro(String token, String carroId);

    public ResponseEntity obterCarro(String carroId);

    public List<Carro> obterTodosCarros();

    public ResponseEntity atualizarCarro(AtualizacaoCarroForm carroForm, String token, String carroId);


}

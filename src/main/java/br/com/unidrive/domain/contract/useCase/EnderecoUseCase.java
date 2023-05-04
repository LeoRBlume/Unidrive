package br.com.unidrive.domain.contract.useCase;

import br.com.unidrive.application.controller.form.EnderecoForm;
import br.com.unidrive.domain.model.Endereco;

public interface EnderecoUseCase {

    public Endereco cadastrarEndereco(EnderecoForm enderecoForm);
}

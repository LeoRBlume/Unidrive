package br.com.unidrive.application.useCase;

import br.com.unidrive.application.controller.form.EnderecoForm;
import br.com.unidrive.domain.model.Endereco;
import br.com.unidrive.infrastructure.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoUseCase {

    @Autowired
    EnderecoRepository enderecoRepository;

    public Endereco cadastrarEndereco(EnderecoForm enderecoForm){
        var endereco = new Endereco(enderecoForm);
        return enderecoRepository.save(endereco);
    }
}

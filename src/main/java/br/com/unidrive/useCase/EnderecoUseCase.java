package br.com.unidrive.useCase;

import br.com.unidrive.controller.form.EnderecoForm;
import br.com.unidrive.model.Endereco;
import br.com.unidrive.repository.EnderecoRepository;
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

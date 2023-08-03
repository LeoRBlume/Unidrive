package br.com.unidrive.application.controller.dto;

import br.com.unidrive.domain.model.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaMarcaCarrosDto {

    private String marca;
    List<Carro> carros;

}

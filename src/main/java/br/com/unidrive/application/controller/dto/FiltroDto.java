package br.com.unidrive.application.controller.dto;

import br.com.unidrive.domain.model.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class FiltroDto {

    List<ListaMarcaCarrosDto> filtros;

    public FiltroDto() {
        this.filtros = new ArrayList<>();
    }
}
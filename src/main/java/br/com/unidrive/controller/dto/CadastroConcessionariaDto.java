package br.com.unidrive.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class CadastroConcessionariaDto {
    private HttpStatus status;
    private String resposta;

}

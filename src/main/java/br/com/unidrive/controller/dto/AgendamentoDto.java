package br.com.unidrive.controller.dto;

import br.com.unidrive.model.Agendamento;
import br.com.unidrive.model.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class AgendamentoDto {

    @NotNull
    String dt_inicial;

    @NotNull
    String dt_final;

    @ManyToOne
    Carro carro;

    public static List<AgendamentoDto> transformarListaAgendamento(List<Agendamento> allByCarro) {

        var listaDto = new ArrayList<AgendamentoDto>();

        for (Agendamento a : allByCarro){
            listaDto.add(new AgendamentoDto(a.getDt_inicial(), a.getDt_final(), a.getCarro()));
        }
        return listaDto;
    }
}

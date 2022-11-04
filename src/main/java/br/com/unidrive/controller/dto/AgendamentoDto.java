package br.com.unidrive.controller.dto;

import br.com.unidrive.model.Agendamento;
import br.com.unidrive.model.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class AgendamentoDto {

    public String dt_agendamento;

    public String hr_inicial;

    public String hr_final;

    @ManyToOne
    Carro carro;

    public static List<AgendamentoDto> transformarListaAgendamento(List<Agendamento> allByCarro) {

        var listaDto = new ArrayList<AgendamentoDto>();

        for (Agendamento a : allByCarro){
            listaDto.add(new AgendamentoDto(a.getDt_agendamento(), a.getHr_inicial(), a.getHr_final(),a.getCarro()));
        }
        return listaDto;
    }
}

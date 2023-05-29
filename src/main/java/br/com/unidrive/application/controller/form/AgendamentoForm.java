package br.com.unidrive.application.controller.form;

import br.com.unidrive.domain.model.Carro;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AgendamentoForm {

    public String dt_agendamento;
    public String hr_inicial;
    public String hr_final;
    public Carro carro;

}

package br.com.unidrive.controller.form;

import br.com.unidrive.model.Carro;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AgendamentoForm {

    public String dt_inicial;
    public String dt_final;
    public Carro carro;

}

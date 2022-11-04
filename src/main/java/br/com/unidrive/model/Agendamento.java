package br.com.unidrive.model;

import br.com.unidrive.controller.form.AgendamentoForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    public String dt_agendamento;

    public String hr_inicial;

    public String hr_final;

    @ManyToOne
    Carro carro;

    public Agendamento(Usuario usuario, AgendamentoForm agendamentoForm) {
        this.usuario = usuario;
        this.dt_agendamento = agendamentoForm.dt_agendamento;
        this.hr_inicial = agendamentoForm.hr_inicial;
        this.hr_final = agendamentoForm.hr_final;
        this.carro = agendamentoForm.carro;
    }
}

package br.com.unidrive.domain.model;

import br.com.unidrive.controller.form.AgendamentoForm;
import br.com.unidrive.domain.Carro;
import br.com.unidrive.domain.Usuario;
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

    private String dt_agendamento;

    private String hr_inicial;

    private String hr_final;

    @ManyToOne
    private Carro carro;

    public Agendamento(Usuario usuario, AgendamentoForm agendamentoForm) {
        this.usuario = usuario;
        this.dt_agendamento = agendamentoForm.dt_agendamento;
        this.hr_inicial = agendamentoForm.hr_inicial;
        this.hr_final = agendamentoForm.hr_final;
        this.carro = agendamentoForm.carro;
    }
}

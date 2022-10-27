package br.com.unidrive.model;

import br.com.unidrive.controller.form.AgendamentoForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull
    String dt_inicial;

    @NotNull
    String dt_final;

    @ManyToOne
    Carro carro;

    public Agendamento(Usuario usuario, AgendamentoForm agendamentoForm) {
        this.usuario = usuario;
        this.dt_inicial = agendamentoForm.dt_inicial;
        this.dt_final = agendamentoForm.dt_final;
        this.carro = agendamentoForm.carro;
    }
}

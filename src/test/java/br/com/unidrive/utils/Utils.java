package br.com.unidrive.utils;

import br.com.unidrive.domain.model.Agendamento;
import br.com.unidrive.domain.model.Concessionaria;
import br.com.unidrive.domain.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static Concessionaria getConcecionaria() {
        return new Concessionaria();
    }

    public static Usuario getUsuario() {
        var usuario =  new Usuario();

        usuario.setId(1L);
        usuario.setCpf("50978008855");
        usuario.setConcessionaria(getConcecionaria());

        return usuario;
    }

    public static List<Agendamento> getListAgendamento() {

        var agendamento = new Agendamento();

        var listaAgendamento = new ArrayList<Agendamento>();

        listaAgendamento.add(agendamento);
        listaAgendamento.add(agendamento);

        return listaAgendamento;

    }
}

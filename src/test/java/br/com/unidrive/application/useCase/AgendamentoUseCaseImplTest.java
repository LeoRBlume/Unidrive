package br.com.unidrive.application.useCase;

import br.com.unidrive.infrastructure.repository.AgendamentoRepository;
import br.com.unidrive.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AgendamentoUseCaseImplTest {

    @Mock
    UsuarioUseCaseImpl usuarioUseCase;

    @Mock
    AgendamentoRepository agendamentoRepositoryMock;

    @InjectMocks
    AgendamentoUseCaseImpl agendamentoUseCase;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    void obterAgendamentosPorUsuarioTest() {

        when(agendamentoRepositoryMock.findAllByUsuario(any())).thenReturn(Utils.getListAgendamento());

        var usuario = Utils.getUsuario();

        var response = agendamentoUseCase.obterAgendamentosPorUsuario(usuario);

        assertEquals(response.size(), 2);
    }

    @Test
    void obterAgendamentosPorUsuarioToken() {

        when(agendamentoRepositoryMock.findAllByUsuario(any())).thenReturn(Utils.getListAgendamento());

        when(usuarioUseCase.obterUsuarioPorToken(anyString())).thenReturn(Utils.getUsuario());

        var response = agendamentoUseCase.obterAgendamentosPorUsuarioToken("eqw8e79qw8");

        assertEquals(response.size(), 2);

    }



    @Test
    void obterAgendamentosPorConcessionariaToken() {

        //when(usuarioUseCase.obterUsuarioPorToken(anyString())).thenReturn(Utils.getUsuario());

    }
}
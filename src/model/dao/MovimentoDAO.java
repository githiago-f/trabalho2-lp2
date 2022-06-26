package model.dao;

import model.Correspondencia;
import model.Destinatario;
import model.Movimento;
import view.errors.NaoEncontrado;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MovimentoDAO implements OperacoesDAO {
    private static final List<Movimento> movimentosList = new ArrayList<>();

    @Override
    public void inserir(Object obj) {
        movimentosList.add((Movimento) obj);
    }

    @Override
    public void excluir(Object obj) {
        movimentosList.remove((Movimento) obj);
    }

    @Override
    public void editar(Object newObj) throws NaoEncontrado {
        int index = movimentosList.indexOf((Movimento) newObj);
        if (index == -1) {
            throw new NaoEncontrado("Movimento");
        }
        movimentosList.set(index, (Movimento) newObj);
    }

    @Override
    public List<Movimento> pesquisar() {
        return movimentosList;
    }

    public List<Movimento> pesquisarPorDestinatario(Destinatario destinatario) {
        List<Movimento> resultado = new ArrayList<>();
        for (Movimento movimento : movimentosList) {
            Correspondencia correspondencia = movimento.getCorrespondencia();
            if (correspondencia.getDestino().equals(destinatario)) {
                resultado.add(movimento);
            }
        }
        return resultado;
    }

    private boolean compareCalendars(Calendar calendar, Calendar calendar2) {
        return calendar.get(Calendar.DATE) == calendar2.get(Calendar.DATE) &&
            calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
            calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }

    public List<Movimento> pesquisarPorData(Calendar calendar) {
        List<Movimento> movimentos = new ArrayList<>();
        for (Movimento movimento : movimentosList) {
            if(compareCalendars(calendar, movimento.getData())) {
                movimentos.add(movimento);
            }
        }
        return movimentos;
    }
}

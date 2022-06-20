package model.dao;

import model.Correspondencia;
import model.Destinatario;
import view.errors.NaoEncontrado;

import java.util.ArrayList;
import java.util.List;

public class CorrespondenciaDAO implements OperacoesDAO {
    private static final List<Correspondencia> correspondenciaList = new ArrayList<>();

    @Override
    public void inserir(Object obj) {
        correspondenciaList.add((Correspondencia) obj);
    }

    @Override
    public void excluir(Object obj) {
        correspondenciaList.remove((Correspondencia) obj);
    }

    @Override
    public void editar(Object newObj) throws NaoEncontrado {
        int idx = correspondenciaList.indexOf((Correspondencia) newObj);
        if (idx == -1) {
            throw new NaoEncontrado("Correspondencia");
        }
        correspondenciaList.set(idx, (Correspondencia) newObj);
    }

    @Override
    public List<Correspondencia> pesquisar() {
        return correspondenciaList;
    }

    public List<Correspondencia> pesquisarPorDestinatario(Destinatario destinatario) {
        List<Correspondencia> correspondencias = new ArrayList<>();
        for(Correspondencia c : correspondenciaList) {
            if(!c.getStatus() && c.getDestino().equals(destinatario)) {
                correspondencias.add(c);
            }
        }
        return correspondencias;
    }
}
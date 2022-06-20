package model.dao;

import model.Destinatario;
import model.dao.errors.NaoEDestinatario;
import view.errors.NaoEncontrado;

import java.util.*;

public class DestinatarioDAO implements OperacoesDAO {
    private static final Map<String, Destinatario> destinatarioMap = new HashMap<>();

    private static String getKey(String nome) {
        return nome.toLowerCase().replaceAll("/\s/", "_");
    }

    public void inserir(Object obj) {
        if(!(obj instanceof Destinatario)) {
            throw new NaoEDestinatario();
        }
        Destinatario destinatario = (Destinatario) obj;
        destinatarioMap.put(
                getKey(destinatario.getNome()),
                destinatario
        );
    }

    public void excluir(Object obj) {
        if(!(obj instanceof Destinatario)) {
            throw new NaoEDestinatario();
        }
        Destinatario destinatario = (Destinatario) obj;
        destinatarioMap.remove(getKey(destinatario.getNome()));
    }

    public void editar(Object newObj) throws NaoEncontrado {
        if(!(newObj instanceof Destinatario)) {
            throw new NaoEDestinatario();
        }
        Destinatario destinatario = (Destinatario) newObj;
        if(!destinatarioMap.containsKey(getKey(destinatario.getNome()))) {
            throw new NaoEncontrado("Destinatario");
        }
        destinatarioMap.put(
                getKey(destinatario.getNome()),
                destinatario
        );
    }

    @Override
    public List<Destinatario> pesquisar() {
        return new ArrayList<>(destinatarioMap.values());
    }

    public Destinatario pesquisarPorNome(String nome) {
        if(!destinatarioMap.containsKey(getKey(nome))) {
            return null;
        }
        return destinatarioMap.get(getKey(nome));
    }

    public List<Destinatario> pesquisarPorNumero(String numeroApartamento) {
        List<Destinatario> destinatarios = pesquisar();
        List<Destinatario> result = new ArrayList<>();
        for (Destinatario des : destinatarios) {
            if (des.getNumeroImovel().equals(numeroApartamento)) {
                result.add(des);
            }
        }
        return result;
    }
}

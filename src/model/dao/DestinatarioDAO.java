package model.dao;

import model.Destinatario;
import model.dao.errors.NaoEDestinatario;
import view.errors.NaoEncontrado;

import java.util.*;

public class DestinatarioDAO implements OperacoesDAO {
    private static final Map<String, Destinatario> destinatarioMap = new HashMap<>();

    private static String getKey(String cpf) {
        return cpf.toLowerCase();
    }

    public void inserir(Object obj) {
        if(!(obj instanceof Destinatario)) {
            throw new NaoEDestinatario();
        }
        Destinatario destinatario = (Destinatario) obj;
        destinatarioMap.put(
                getKey(destinatario.getCpf()),
                destinatario);
    }

    public void excluir(Object obj) {
        if(!(obj instanceof Destinatario)) {
            throw new NaoEDestinatario();
        }
        Destinatario destinatario = (Destinatario) obj;
        destinatarioMap.remove(getKey(destinatario.getCpf()));
    }

    public void editar(Object newObj) throws NaoEncontrado {
        if(!(newObj instanceof Destinatario)) {
            throw new NaoEDestinatario();
        }
        Destinatario destinatario = (Destinatario) newObj;
        String key = getKey(destinatario.getCpf());
        if(!destinatarioMap.containsKey(key)) {
            throw new NaoEncontrado("Destinatario");
        }
        destinatarioMap.put(
                key,
                destinatario
        );
    }

    @Override
    public List<Destinatario> pesquisar() {
        return new ArrayList<>(destinatarioMap.values());
    }

    public List<Destinatario> pesquisarPorNome(String nome) {
        List<Destinatario> destinatarios = pesquisar();
        List<Destinatario> result = new ArrayList<>();
        for (Destinatario des : destinatarios) {
            if (des.getNome().equalsIgnoreCase(nome)) {
                result.add(des);
            }
        }
        return result;
    }

    public List<Destinatario> pesquisarPorNumero(String numeroImovel) {
        List<Destinatario> destinatarios = pesquisar();
        List<Destinatario> result = new ArrayList<>();
        for (Destinatario des : destinatarios) {
            if (des.getNumeroImovel().equalsIgnoreCase(numeroImovel)) {
                result.add(des);
            }
        }
        return result;
    }

    public Destinatario pesquisaPorCpf(String cpf) {
        String key = getKey(cpf);
        if(!destinatarioMap.containsKey(key)) {
            return null;
        }
        return destinatarioMap.get(key);
    }
}

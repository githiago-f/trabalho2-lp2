package view;

import controle.Comando;
import controle.Processador;
import model.Destinatario;
import model.dao.DestinatarioDAO;
import view.errors.PropriedadeInvalida;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InterfacePesquisarDestinatario extends InterfaceBase implements Comando {
    private final DestinatarioDAO destinatarioDAO = new DestinatarioDAO();

    @Override
    public void executar() {
        try {
            String nomeOuImovel = leDados("Pesquisar por nome ou imovel?\n1) nome\n2) imovel");
            List<Destinatario> destinatarios = new ArrayList<>();
            switch (nomeOuImovel) {
                case "1":
                    destinatarios.add(buscaPorNome());
                    break;
                case "2":
                    destinatarios.addAll(buscaPorNumeroDoImovel());
                    break;
                default:
                    throw new PropriedadeInvalida(nomeOuImovel, "Destinatário");
            }
            JOptionPane.showMessageDialog(null, asString(destinatarios));
            Processador.direcionar("0");
        } catch (CampoVazioException | PropriedadeInvalida e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private String asString(List<Destinatario> destinatarios) {
        StringBuilder sb = new StringBuilder();
        for (Destinatario destinatario : destinatarios) {
            sb.append(destinatario).append("\n");
        }
        return sb.toString();
    }

    private List<Destinatario> buscaPorNumeroDoImovel() throws CampoVazioException {
        List<Destinatario> destinatarios;
        String numeroDoImovel = leDados("Insira o numero do imóvel: ");
        destinatarios = destinatarioDAO.pesquisarPorNumero(numeroDoImovel);
        return destinatarios;
    }

    private Destinatario buscaPorNome() throws CampoVazioException {
        String nome = leDados("Nome do destinatário: ");
        return destinatarioDAO.pesquisarPorNome(nome);
    }
}

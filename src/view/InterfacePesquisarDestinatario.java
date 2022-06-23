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
            String nomeOuImovel = leDadosRetry("Pesquisar por nome ou imovel?\n1) nome\n2) imovel");
            List<Destinatario> destinatarios = new ArrayList<>();
            switch (nomeOuImovel) {
                case "1":
                    destinatarios.addAll(buscaPorNome());
                    break;
                case "2":
                    destinatarios.add(buscaPorNumeroDoImovel());
                    break;
                default:
                    throw new PropriedadeInvalida(nomeOuImovel, "Destinatário");
            }
            JOptionPane.showMessageDialog(null, asString(destinatarios));
        } catch (PropriedadeInvalida e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private Destinatario buscaPorNumeroDoImovel() {
        String numeroDoImovel = leDadosRetry("Insira o numero do imóvel: ");
        return destinatarioDAO.pesquisarPorNumero(numeroDoImovel);
    }

    private List<Destinatario> buscaPorNome() {
        String nome = leDadosRetry("Nome do destinatário: ");
        return destinatarioDAO.pesquisarPorNome(nome);
    }
}

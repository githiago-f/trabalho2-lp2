package view;

import controle.Comando;
import controle.Processador;
import model.*;
import model.dao.CorrespondenciaDAO;
import model.dao.DestinatarioDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InterfacePesquisarCorrespondencia extends InterfaceBase implements Comando{
    private final DestinatarioDAO destinatarioDAO = new DestinatarioDAO();
    private final CorrespondenciaDAO correspondenciaDAO = new CorrespondenciaDAO();

    @Override
    public void executar() {
        // TODO: implementar as ações necessárias para pesquisar se existem correspondência
        //  não entregues para um determinado destinatário, identificando se são cartas ou pacotes
        try {
            String nome = leDados("");
            Destinatario destinatario = destinatarioDAO.pesquisarPorNome(nome);
            List<Correspondencia> correspondencias = correspondenciaDAO.pesquisarPorDestinatario(destinatario);
            JOptionPane.showMessageDialog(null, asString(correspondencias));
            Processador.direcionar("0");
        } catch (CampoVazioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    static String asString(List<Correspondencia> correspondencias) {
        StringBuilder sb = new StringBuilder();
        for (Correspondencia correspondencia : correspondencias) {
            sb.append(correspondencia).append('\n');
        }
        return sb.toString();
    }

}

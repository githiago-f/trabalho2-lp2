package view;

import controle.Comando;
import model.*;
import model.dao.CorrespondenciaDAO;
import model.dao.DestinatarioDAO;

import javax.swing.*;
import java.util.List;

public class InterfacePesquisarCorrespondencia extends InterfaceBase implements Comando{
    private final DestinatarioDAO destinatarioDAO = new DestinatarioDAO();
    private final CorrespondenciaDAO correspondenciaDAO = new CorrespondenciaDAO();

    @Override
    public void executar() {
        String cpf = leDadosRetry("Informe o cpf do destinatario");
        Destinatario destinatario = destinatarioDAO.pesquisaPorCpf(cpf);
        List<Correspondencia> correspondencias = correspondenciaDAO.pesquisarNaoEntreguesPorDestinatario(destinatario);
        if(correspondencias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma correspondencia para ser entrege.");
        } else {
            JOptionPane.showMessageDialog(null, asString(correspondencias));
        }
    }
}

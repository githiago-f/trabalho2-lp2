package view;

import controle.Comando;
import controle.Processador;
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
        JOptionPane.showMessageDialog(null, asString(correspondencias));
    }
}

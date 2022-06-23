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
        // TODO: implementar as ações necessárias para pesquisar se existem correspondência
        //  não entregues para um determinado destinatário, identificando se são cartas ou pacotes
        String numeroImovel = leDadosRetry("Informe o numero do imóvel");
        Destinatario destinatario = destinatarioDAO.pesquisarPorNumero(numeroImovel);
        List<Correspondencia> correspondencias = correspondenciaDAO.pesquisarNaoEntreguesPorDestinatario(destinatario);
        JOptionPane.showMessageDialog(null, asString(correspondencias));
    }

}

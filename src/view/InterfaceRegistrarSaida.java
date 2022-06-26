package view;

import controle.Comando;
import model.Correspondencia;
import model.Destinatario;
import model.Movimento;
import model.dao.CorrespondenciaDAO;
import model.dao.DestinatarioDAO;
import model.dao.MovimentoDAO;
import view.errors.NaoEncontrado;
import view.errors.NaoPodeRetirar;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InterfaceRegistrarSaida extends InterfaceBase implements Comando {
    private final DestinatarioDAO destinatarioDAO = new DestinatarioDAO();
    private final CorrespondenciaDAO correspondenciaDAO = new CorrespondenciaDAO();
    private final MovimentoDAO movimentoDAO = new MovimentoDAO();

    public void executar() {
        try {
            String quemRegistraSaida = leDadosRetry("Informe quem registra");
            String cpfDestinatario = leDadosRetry("Informe o cpf do destinatario");
            Destinatario destinatario = destinatarioDAO.pesquisaPorCpf(cpfDestinatario);
            if (destinatario == null) {
                throw new NaoEncontrado("Destinatario");
            }
            List<Correspondencia> naoEntregues = correspondenciaDAO.pesquisarNaoEntreguesPorDestinatario(destinatario);
            if(naoEntregues.isEmpty()) {
                throw new NaoEncontrado("Correspondecia");
            }
            String retirante = leDadosRetry("Informe o cpf de quem retira");
            for (Correspondencia correspondencia : naoEntregues) {
                if (!correspondencia.podeRetirar(retirante)) {
                    throw new NaoPodeRetirar();
                }
                Movimento movimento = new Movimento(correspondencia, retirante, quemRegistraSaida);
                movimentoDAO.inserir(movimento);
                correspondenciaDAO.editar(correspondencia);
            }
            JOptionPane.showMessageDialog(null, "Entregue :)");
        } catch(NaoPodeRetirar | NaoEncontrado e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

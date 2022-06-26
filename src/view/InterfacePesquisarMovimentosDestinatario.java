package view;

import controle.*;
import model.Destinatario;
import model.Movimento;
import model.dao.CorrespondenciaDAO;
import model.dao.DestinatarioDAO;
import model.dao.MovimentoDAO;
import view.errors.NaoEncontrado;

import javax.swing.*;
import java.util.List;


public class InterfacePesquisarMovimentosDestinatario extends InterfaceBase implements Comando {

    private final CorrespondenciaDAO correspondenciaDAO = new CorrespondenciaDAO();
    private final MovimentoDAO movimentoDAO = new MovimentoDAO();
    private final DestinatarioDAO destinatarioDAO = new DestinatarioDAO();

    public void executar() {
        try {
            String cpfDestinatario = leDadosRetry("Informe o cpf do destinatario");
            Destinatario destinatario = destinatarioDAO.pesquisaPorCpf(cpfDestinatario);
            if (destinatario == null) {
                throw new NaoEncontrado("Destinatario");
            }
            List<Movimento> movimentos = movimentoDAO.pesquisarPorDestinatario(destinatario);
            if (movimentos.isEmpty()){
                JOptionPane.showMessageDialog(null, "Sem correspondencias");
            }
            else {
                JOptionPane.showMessageDialog(null, asString(movimentos));
            }

        } catch(NaoEncontrado e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

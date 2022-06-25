package view;

import controle.Comando;
import controle.Processador;
import model.Destinatario;
import model.dao.DestinatarioDAO;
import model.dao.errors.NaoEDestinatario;
import view.errors.NaoEncontrado;

import javax.swing.*;

public class InterfaceExcluirDestinatario extends InterfaceBase implements Comando {
    private final DestinatarioDAO destinatarioDAO = new DestinatarioDAO();

    @Override
    public void executar() {
        try {
            String cpf = leDadosRetry("Informe o cpf do destinatário a ser removido");
            Destinatario destinatario = destinatarioDAO.pesquisaPorCpf(cpf);
            if(destinatario == null) {
                throw new NaoEncontrado("Destinatario");
            }
            destinatarioDAO.excluir(destinatario);
            JOptionPane.showMessageDialog(null, "Destinatário removido!");
        } catch (NaoEncontrado | NaoEDestinatario e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage() +
                    ". Você será redirecionado para a primeira tela");
        }
    }
}

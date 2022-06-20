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
            String nome = leDados("Nome do destinatario a ser removido: ");
            Destinatario destinatario = destinatarioDAO.pesquisarPorNome(nome);
            if(destinatario == null) {
                throw new NaoEncontrado("Destinatario");
            }
            destinatarioDAO.excluir(destinatario);
            Processador.direcionar("0");
        } catch (NaoEncontrado | NaoEDestinatario | CampoVazioException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

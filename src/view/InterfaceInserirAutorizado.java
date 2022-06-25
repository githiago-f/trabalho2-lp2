package view;

import controle.Comando;
import controle.Processador;
import model.Destinatario;
import model.dao.DestinatarioDAO;
import model.dao.errors.NaoEDestinatario;
import view.errors.NaoEncontrado;

import javax.swing.*;

public class InterfaceInserirAutorizado extends InterfaceBase implements Comando {
    private final DestinatarioDAO destinatarioDAO = new DestinatarioDAO();

    @Override
    public void executar() {
        try {
            String cpf = leDadosRetry("Informe o cpf do destinatário:");
            Destinatario destinatario = destinatarioDAO.pesquisaPorCpf(cpf);
            if(destinatario == null) {
                throw new NaoEncontrado("Destinatario");
            }
            String autorizado = leDadosRetry("Informe o cpf do(a) autorizado(a)?");
            destinatario.addAutorizado(autorizado);
            destinatarioDAO.editar(destinatario);
            String message = autorizado + " foi autorizado a retirar itens para " + destinatario.getNome();
            JOptionPane.showMessageDialog(null, message);
        } catch (NaoEncontrado | NaoEDestinatario e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage() +
                    ". Você será redirecionado para a primeira tela");
        }
    }
}

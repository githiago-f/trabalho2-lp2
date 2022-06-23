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
            String nome = leDadosRetry("Informe o numero do imovel do destinatário:");
            Destinatario destinatario = destinatarioDAO.pesquisarPorNumero(nome);
            if(destinatario == null) {
                throw new NaoEncontrado("Destinatario");
            }
            String autorizado = leDadosRetry("Informe o nome do(a) autorizado(a)?");
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

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
            String nome = leDados("Qual o nome do destinatário?");
            Destinatario destinatario = destinatarioDAO.pesquisarPorNome(nome);
            if(destinatario == null) {
                throw new NaoEncontrado("Destinatario");
            }
            String autorizado = leDados("Qual o nome do(a) autorizado(a)?");
            destinatario.addAutorizado(autorizado);
            destinatarioDAO.editar(destinatario);
            String message = autorizado + " foi autorizado a retirar itens para " + destinatario.getNome();
            JOptionPane.showMessageDialog(null, message);
            Processador.direcionar("0");
        } catch (NaoEncontrado | NaoEDestinatario | CampoVazioException cve) {
            cve.printStackTrace();
            JOptionPane.showMessageDialog(null, cve.getMessage());
        }
    }
}

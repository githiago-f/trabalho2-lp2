package view;

import controle.Comando;
import controle.Processador;
import model.Destinatario;
import model.dao.DestinatarioDAO;
import model.dao.errors.NaoEDestinatario;

import javax.swing.*;

public class InterfaceInserirDestinatario extends InterfaceBase implements Comando {
    private final DestinatarioDAO destinatarioDAO = new DestinatarioDAO();

    @Override
    public void executar() {
        try {
            String nome = leDados("Nome do destinatário: ");
            String numeroDoImovel = leDados("Insira o numero do imóvel: ");
            Destinatario destinatario = new Destinatario(nome, numeroDoImovel);
            destinatarioDAO.inserir(destinatario);
            Processador.direcionar("0");
        } catch (CampoVazioException | NaoEDestinatario exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}

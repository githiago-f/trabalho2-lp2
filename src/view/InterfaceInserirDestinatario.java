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
            String cpf = leDadosRetry("Insira o cpf do destinatario");
            String nome = leDadosRetry("Insira o nome do destinatario");
            String numeroDoImovel = leDadosRetry("Insira o numero do imóvel");
            Destinatario destinatario = new Destinatario(cpf, nome, numeroDoImovel);
            destinatarioDAO.inserir(destinatario);
            JOptionPane.showMessageDialog(null, "Destinatário " + nome + " inserido!");
        } catch (NaoEDestinatario exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}

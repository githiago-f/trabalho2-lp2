package view;

import javax.swing.*;
import controle.Comando;
import model.*;
import model.dao.CorrespondenciaDAO;
import model.dao.DestinatarioDAO;
import model.dao.MovimentoDAO;
import view.errors.NaoEncontrado;

import java.util.List;

public class InterfaceRegistrarEntrada extends InterfaceBase implements Comando {
    private final DestinatarioDAO destinatarioDAO = new DestinatarioDAO();
    private final CorrespondenciaDAO correspondenciaDAO = new CorrespondenciaDAO();
    private final MovimentoDAO movimentoDAO = new MovimentoDAO();

    public void executar() {
        String quemRecebe = leDadosRetry("Informe o nome de quem esta recebendo essa correspondencia");
        boolean tentar = true;
        while (tentar) {
            try {
                String numeroDoAp = leDadosRetry("Informe o numero do ap do destinatario");

                List<Destinatario> destinatarios = destinatarioDAO.pesquisarPorNumero(numeroDoAp);
                if (destinatarios.isEmpty()) {
                    throw new NaoEncontrado("Destinatario");
                }
                tentar = false;
                Destinatario destinatario = destinatarios.get(0); // só uma pessoa por ap
                Correspondencia correspondencia = novaCartaOuPacote(destinatario);
                correspondenciaDAO.inserir(correspondencia);
                movimentoDAO.inserir(new Movimento(correspondencia, quemRecebe));
                JOptionPane.showMessageDialog(null, "Registrada entrada da correspondencia");
            } catch (NaoEncontrado e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                tentar = escolha("Tentar outro?\n1) Sim\n2) Nao");
            }
        }
    }

    private Correspondencia novaCartaOuPacote(Destinatario destinatario) {
        boolean eCarta = escolha("Informe se e carta ou pacote:\n1) Carta\n2) Pacote");
        if(eCarta) {
            boolean temRecibo = escolha("Recebeu um recibo?\n1) Sim\n2) Nao");
            return new Carta(destinatario, temRecibo);
        } else {
            String empresa = leDadosRetry("Informe a empresa que enviou o pacote");
            return new Pacote(destinatario, empresa);
        }
    }
}

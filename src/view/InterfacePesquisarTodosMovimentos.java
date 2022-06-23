package view;

import controle.Comando;
import model.Movimento;
import model.dao.MovimentoDAO;

import javax.swing.*;
import java.util.List;

public class InterfacePesquisarTodosMovimentos extends InterfaceBase implements Comando {
    private final MovimentoDAO movimentoDAO = new MovimentoDAO();
    @Override
    public void executar() {
        List<Movimento> movimentos = movimentoDAO.pesquisar();
        JOptionPane.showMessageDialog(null, asString(movimentos));
        voltarAoMenuPrincipal();
    }
}

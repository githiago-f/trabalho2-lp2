package view;

import controle.Comando;
import model.Movimento;
import model.dao.MovimentoDAO;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class InterfacePesquisarMovimentosData extends InterfaceBase implements Comando {
    private final MovimentoDAO movimentoDAO = new MovimentoDAO();
    @Override
    public void executar() {
        try {
            Calendar calendar = leData();
            List<Movimento> movimentos = movimentoDAO.pesquisarPorData(calendar);
            if (movimentos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma movimentação nesta data");
            } else {
                JOptionPane.showMessageDialog(null, asString(movimentos));
            }
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private Calendar leData() throws NumberFormatException {
        String data = leDadosRetry("Informe a data no formato DD/MM/YYYY");
        Calendar calendar = GregorianCalendar.getInstance();
        String[] strings = data.split("/");
        int date = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
        int year = Integer.parseInt(strings[2]);
        if (month < 1 || month > 12) {
            throw new NumberFormatException("Numero devia estar entre 0 e 11");
        }
        calendar.set(year, month-1, date);
        return calendar;
    }
}

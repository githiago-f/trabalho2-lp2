package view;

import controle.Processador;

import javax.swing.*;
import java.util.List;

public class InterfaceBase {

    public static String leDados(String mensagem) throws CampoVazioException {
        String opcao = JOptionPane.showInputDialog(null, mensagem);
        if (opcao.isBlank() || opcao.isEmpty()) {
            throw new CampoVazioException(mensagem);
        } else {
            return opcao;
        }
    }

    public static void voltarAoMenuPrincipal() {
        int voltarAoMenu = JOptionPane.showConfirmDialog(
                null,
                "Voltar ao menu principal?"
        );
        if(voltarAoMenu == JOptionPane.YES_OPTION) {
            Processador.direcionar("0");
        } else {
            System.exit(0);
        }
    }

    public static boolean escolha(String message) {
        while(true) {
            try {
                String res = JOptionPane.showInputDialog(message);
                if (res.isBlank() || res.isEmpty()) {
                    throw new CampoVazioException("Campo vazio, insira novamente o valor");
                }
                if (res.equals("1")) {
                    return true;
                } else if (res.equals("2")) {
                    return false;
                } else {
                    throw new NumberFormatException("Numero inválido");
                }
            } catch (CampoVazioException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public static String asString(List<?> objects) {
        StringBuilder sb = new StringBuilder();
        for (Object o : objects) {
            sb.append(o).append("\n");
        }
        return sb.toString();
    }

    public static String leDadosRetry(String message) {
        boolean retry = true;
        while (retry) {
            try {
                return leDados(message);
            } catch (CampoVazioException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " novamente!");
                retry = escolha("Tentar novamente?\n1) Sim\n2) Não");
            }
        }
        voltarAoMenuPrincipal();
        System.exit(0);
        return null;
    }
}

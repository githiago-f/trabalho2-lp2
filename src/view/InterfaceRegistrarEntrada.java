package view;

import javax.swing.*;
import controle.Comando;
import controle.Processador;
import model.*;

import java.sql.SQLException;

public class InterfaceRegistrarEntrada extends InterfaceBase implements Comando {

    public void executar() {
        String quemRecebe = null;
        String numeroImovel = null;
        String sair = null;
        boolean teste = true;


        teste = true;
        do {
            try {
                numeroImovel = leDados("Informe o número do imóvel do destinatário");
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }
        } while (teste);

        teste = true;
        do {
            try {
                quemRecebe = leDados("Informe o nome de quem está recebendo essa correspondencia");
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }
        } while (teste);

            System.out.println("Esse é o resultado da funcionalidade 2");

        teste = true;
        do {
            try {
                sair = leDados("Digite 0 para voltar");
                int i = Integer.parseInt(sair);
                if (i != 0) continue;
                if (i == 0) {
                    Processador.direcionar("0");
                }
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            } catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, nfe.getMessage() + " Isso não é um número inteiro");
            }
        } while (teste);


    }

}

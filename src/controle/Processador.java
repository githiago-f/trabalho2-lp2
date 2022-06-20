/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.HashMap;

/**
 *
 * @author KAREN
 */
public class Processador {

    private static final HashMap<String, String> comandos = new HashMap<>();

    static {
        comandos.put("0", "view.InterfacePrincipal");
        comandos.put("1", "view.InterfaceSair");
        comandos.put("2", "view.InterfaceRegistrarEntrada");
        comandos.put("3", "view.InterfaceRegistrarSaida.java");
        comandos.put("4", "view.InterfacePesquisarCorrespondencia");
        comandos.put("5", "view.InterfacePesquisarMovimentosDestinatario");
        comandos.put("6", "view.InterfacePesquisarMovimentosData");
        comandos.put("7", "view.InterfacePesquisarTodosMovimentos");
        comandos.put("8", "view.InterfaceInserirDestinatario");
        comandos.put("9", "view.InterfaceInserirAutorizado");
        comandos.put("10", "view.InterfacePesquisarDestinatario");
        comandos.put("11", "view.InterfaceExcluirDestinatario");
    }

    public static void direcionar(String cmd) {
        String actionClass = comandos.get(cmd);
        try {
            Class<?> classe = Class.forName(actionClass);
            Comando comando = (Comando) classe.newInstance();
            comando.executar();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
}

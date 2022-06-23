package view;
import controle.Processador;
import model.Destinatario;
import model.dao.DestinatarioDAO;

public class MainTest {
    static Destinatario destinatario(int i) {
        String nome = "Usuario " + i;
        return new Destinatario(nome, String.valueOf(i));
    }

    public static void main(String[] args) {
        DestinatarioDAO destinatarioDAO = new DestinatarioDAO();
        destinatarioDAO.inserir(destinatario(1));
        destinatarioDAO.inserir(destinatario(2));
        destinatarioDAO.inserir(destinatario(3));

        Processador.direcionar("0");
    }
}

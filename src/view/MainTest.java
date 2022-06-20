package view;
import model.Destinatario;
import model.dao.DestinatarioDAO;

public class MainTest {
    static Destinatario destinatario(int i) {
        return new Destinatario("Usuario " + i, "123");
    }

    public static void main(String[] args) {
        try {
            DestinatarioDAO destinatarioDAO = new DestinatarioDAO();
            for (int i = 0; i < 10; i++) {
                Destinatario destinatario = destinatario(i);
                destinatarioDAO.inserir(destinatario);
                if(i % 2 == 0) {
                    destinatario.addAutorizado("Julia");
                    destinatarioDAO.editar(destinatario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

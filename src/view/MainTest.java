package view;
import controle.Processador;
import model.Carta;
import model.Correspondencia;
import model.Destinatario;
import model.dao.CorrespondenciaDAO;
import model.dao.DestinatarioDAO;

public class MainTest {
    public static void main(String[] args) {
        DestinatarioDAO destinatarioDAO = new DestinatarioDAO();
        CorrespondenciaDAO correspondenciaDAO = new CorrespondenciaDAO();

        Destinatario nina = new Destinatario("132", "Nina", "32");
        nina.addAutorizado("133");
        destinatarioDAO.inserir(nina);

        Correspondencia correspondencia = new Carta(nina, true);
        correspondenciaDAO.inserir(correspondencia);

        Processador.direcionar("0");
    }
}

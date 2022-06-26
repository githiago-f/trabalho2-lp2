package view;
import controle.Processador;
import model.Carta;
import model.Correspondencia;
import model.Destinatario;
import model.Pacote;
import model.dao.CorrespondenciaDAO;
import model.dao.DestinatarioDAO;

public class MainTest {
    public static void main(String[] args) {
        DestinatarioDAO destinatarioDAO = new DestinatarioDAO();
        CorrespondenciaDAO correspondenciaDAO = new CorrespondenciaDAO();

        Destinatario nina = new Destinatario("132", "Nina", "32");
        nina.addAutorizado("133");
        destinatarioDAO.inserir(nina);
        Destinatario carminha = new Destinatario("221", "Carminha", "21");
        carminha.addAutorizado("222");
        destinatarioDAO.inserir(carminha);
        Destinatario agatha = new Destinatario("112", "Agatha", "12");
        destinatarioDAO.inserir(agatha);

        Correspondencia correspondencia = new Carta(nina, true);
        correspondenciaDAO.inserir(correspondencia);

        Processador.direcionar("0");
    }
}

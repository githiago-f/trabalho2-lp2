package view;
import controle.Processador;
import model.*;
import model.dao.CorrespondenciaDAO;
import model.dao.DestinatarioDAO;
import model.dao.MovimentoDAO;

public class MainTest {
    static DestinatarioDAO destinatarioDAO = new DestinatarioDAO();
    static CorrespondenciaDAO correspondenciaDAO = new CorrespondenciaDAO();
    static MovimentoDAO movimentoDAO = new MovimentoDAO();

    static void novaCarta(Destinatario destinatario) {
        Correspondencia correspondencia = new Carta(destinatario, true);
        correspondenciaDAO.inserir(correspondencia);

        Movimento movimento = new Movimento(correspondencia, "Jose");
        movimentoDAO.inserir(movimento);
    }

    static void novoPacote(Destinatario destinatario, String empresa) {
        Correspondencia correspondencia = new Pacote(destinatario, empresa);
        correspondenciaDAO.inserir(correspondencia);

        Movimento movimento = new Movimento(correspondencia, "Jose");
        movimentoDAO.inserir(movimento);
    }

    public static void main(String[] args) {
        Destinatario nina = new Destinatario("132", "Nina", "32");
        nina.addAutorizado("133");
        destinatarioDAO.inserir(nina);

        Destinatario carminha = new Destinatario("221", "Carminha", "21");
        carminha.addAutorizado("222");
        destinatarioDAO.inserir(carminha);

        Destinatario agatha = new Destinatario("112", "Agatha", "12");
        destinatarioDAO.inserir(agatha);

        novaCarta(nina);
        novaCarta(agatha);
        novoPacote(carminha, "Petz");
        novaCarta(carminha);
        novoPacote(agatha, "Cobasi");
        novoPacote(nina, "Amazon");

        Processador.direcionar("0");
    }
}

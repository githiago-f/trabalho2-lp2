package model.dao.errors;

public class NaoEDestinatario extends IllegalArgumentException {
    public NaoEDestinatario() {
        super("Não é um destinatário!");
    }
}

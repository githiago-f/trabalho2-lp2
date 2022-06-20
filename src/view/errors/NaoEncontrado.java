package view.errors;

public class NaoEncontrado extends Exception {
    public NaoEncontrado(String nomeDaEntidade) {
        super(nomeDaEntidade + " não encontrado(a)");
    }
}

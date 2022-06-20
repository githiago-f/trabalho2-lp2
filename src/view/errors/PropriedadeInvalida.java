package view.errors;

public class PropriedadeInvalida extends Exception {
    public PropriedadeInvalida(String propriedade, String entidade) {
        super("A propriedade " + propriedade + " não existe em: " + entidade);
    }
}

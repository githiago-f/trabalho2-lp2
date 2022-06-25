package view.errors;

public class NaoPodeRetirar extends Exception {
    public NaoPodeRetirar() {
        super("Nao pode retirar a correspondecia!");
    }
}

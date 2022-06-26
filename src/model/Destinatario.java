package model;

import java.util.*;

/**
 * Write a description of class model.Destinatario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Destinatario
{
    private final String cpf;
    private final String nome;
    private final String numeroImovel;
    private final List<String> autorizados;

    public Destinatario(String cpf, String nome, String numeroImovel) {
        this.cpf = cpf;
        this.nome = nome;
        this.numeroImovel = numeroImovel;
        this.autorizados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
        
    public String getNumeroImovel() {
        return numeroImovel;
    }

    public String getCpf() {
        return cpf;
    }

    public String toString() {
        return "Nome = " + nome +
                "\nNumero do Imovel = " + numeroImovel +
                "\nCpf =" + cpf;
    }

    public void addAutorizado(String cpfAutorizado) { autorizados.add(cpfAutorizado); }

    public List<String> getAutorizados() {
        return Collections.unmodifiableList(autorizados);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destinatario that = (Destinatario) o;
        return nome.equals(that.nome) && numeroImovel.equals(that.numeroImovel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, numeroImovel);
    }
}

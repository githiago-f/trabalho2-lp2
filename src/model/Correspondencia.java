package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Write a description of class model.Correspondencia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Correspondencia implements Serializable
{
    private Destinatario destino;
    private boolean status; //TRUE é porque já foi retirado
    
    public Correspondencia (Destinatario destino){
        this.setDestino(destino);
        // por default status é inicializado com false.
    }    
     
    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean getStatus() { return status; }

    public String getTipo() {
        return this instanceof Pacote ? "Pacote" : "Carta";
    }

    @Override
    public String toString() {
        return "Correspondencia{" +
                "destino=" + destino +
                ", tipo=" + getTipo() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correspondencia that = (Correspondencia) o;
        return status == that.status && Objects.equals(destino, that.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destino, status);
    }

    public Destinatario getDestino() {
        return destino;
    }

    public void setDestino(Destinatario destino) {
        this.destino = destino;
    }
}

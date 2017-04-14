package fr.univtln.tbezenger858.Animaux.Mammiferes;

/**
 * Created by Tomy Bezenger on 21/02/2017.
 */
public class Chien extends Mammifere {
    private int nbPattes;

    public Chien() {
    }

    public int getNbPattes() {
        return nbPattes;
    }

    public void setNbPattes(int nbPattes) {
        this.nbPattes = nbPattes;
    }

    public Chien(int id, int nbPattes) {
        super(id);
        this.nbPattes = nbPattes;
    }

    @Override
    public String toString() {
        return "Chien d'id : "+ Integer.toString(this.getId()) + " à " + Integer.toString(this.getNbPattes()) +" pattes." ;

    }
}

package fr.univtln.tbezenger858.Animaux.Mammiferes;

/**
 * Created by Tomy Bezenger on 21/02/2017.
 */
public class Chat extends Mammifere {
    private int nbMoustaches;

    public int getNbMoustaches() {
        return nbMoustaches;
    }

    public void setNbMoustaches(int nbMoustaches) {
        this.nbMoustaches = nbMoustaches;
    }

    public Chat() {
    }

    public Chat(int id, int nbMoustaches) {
        super(id);
        this.nbMoustaches = nbMoustaches;
    }

    @Override
    public String toString() {
        return "Chat d'id : "+ Integer.toString(this.getId()) + " à " + Integer.toString(this.getNbMoustaches()) +" moustaches." ;
    }
}

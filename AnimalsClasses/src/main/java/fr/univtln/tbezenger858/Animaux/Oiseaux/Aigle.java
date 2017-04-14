package fr.univtln.tbezenger858.Animaux.Oiseaux;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Created by Tomy Bezenger on 21/02/2017.
 */
@Entity
@DiscriminatorValue("AIGLE")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, scope = Oiseau.class)
@NamedQueries({
        @NamedQuery(name = Aigle.AIGLE_BY_ALL, query =
                "select aigle from Aigle aigle"),
        @NamedQuery(name = Aigle.AIGLE_BY_ID, query =
                "select aigle from Aigle aigle where aigle = :Pid")
})
public class Aigle extends Oiseau {
    public static final String AIGLE_BY_ALL = "aigleByAll";
    public static final String AIGLE_BY_ID = "aigleById";

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    private Pigeon cible;

    private int nbSerres;

    public Pigeon getCible() {
        return cible;
    }

    public void setCible(Pigeon cible) {
        this.cible = cible;
    }

    public void setNbSerres(int nbSerres) {
        this.nbSerres = nbSerres;
    }

    public int getNbSerres() {
        return nbSerres;
    }


    public Aigle(int nbSerres) {
        this.nbSerres = nbSerres;
        this.cible = null;
    }

    public Aigle(int nbSerres, Pigeon cible) {
        this.nbSerres = nbSerres;
        this.cible = cible;
    }

    public Aigle() {
    }

    @Override
    public String toString() {
        String string = "Aigle d'id : "+ Integer.toString(getId())+" à "+ nbSerres +" serres";
        if (this.cible!=null)
            string+=" qui chasse le pigeon d'id : "+ Integer.toString(cible.getId());
        return  string;
    }
}

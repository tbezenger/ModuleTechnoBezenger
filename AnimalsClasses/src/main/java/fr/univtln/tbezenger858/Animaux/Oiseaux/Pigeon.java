package fr.univtln.tbezenger858.Animaux.Oiseaux;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Tomy Bezenger on 21/02/2017.
 */
@Entity
@DiscriminatorValue("PIGEON")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, scope = Oiseau.class)

@NamedQueries({
        @NamedQuery(name = Pigeon.PIGEON_BY_ALL, query =
                "select pigeon from Pigeon pigeon"),
        @NamedQuery(name = Pigeon.PIGEON_BY_ID, query =
                "select pigeon from Pigeon pigeon where pigeon.id = :Pid")
})
public class Pigeon extends Oiseau {

    public static final String PIGEON_BY_ALL = "pigeonByAll";
    public static final String PIGEON_BY_ID = "pigeonById";

    @OneToMany(mappedBy = "cible",cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    private Collection<Aigle> chasseurs;

    private int nbPlumes;

    public int getNbPlumes() {
        return nbPlumes;
    }

    public void setNbPlumes(int nbPlumes) {
        this.nbPlumes = nbPlumes;
    }

    public Collection<Aigle> getChasseurs() {
        return chasseurs;
    }

    public void addChasseur(Aigle aigle){
        chasseurs.add(aigle);
    }

    public void subChasseurs(Aigle aigle){
        chasseurs.remove(aigle);
    }

    public Pigeon(int nbPlumes) {
        this.nbPlumes = nbPlumes;
        this.chasseurs = null;
    }

    public Pigeon() {
    }

    @Override
    public String toString() {
        return "Pigeon d'id : "+ Integer.toString(getId())+" à " +nbPlumes +
                " plumes chassé par "+ Integer.toString(getChasseurs().size())+ "aigle(s)";
    }
}

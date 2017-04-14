package fr.univtln.tbezenger858.Animaux;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Mammifere;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Aigle;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Oiseau;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Pigeon;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tomy Bezenger on 21/02/2017.
 */
// #PRESENTATION preciser pourquoi avoir renommer en oiseau meme si c'est moche
@Entity
@Table(name = "OISEAU")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "OISEAU_TYPE")
@NamedQueries({
        @NamedQuery(name = Animal.OISEAU_BY_ALL, query =
                "select oiseau from Animal oiseau"),
        @NamedQuery(name = Animal.OISEAU_BY_ID, query =
                "select oiseau from Animal oiseau where oiseau.id = :Pid")
})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, scope = Oiseau.class)
public class Animal implements Serializable{
    public static final String OISEAU_BY_ALL = "oiseauByAll";
    public static final String OISEAU_BY_ID = "oiseauById";

    @Id @GeneratedValue
    private int id;

    public int getId() {
        return id;
    }

    public Animal() {
    }

    public Animal(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                '}';
    }
}

package fr.univtln.tbezenger858.Animaux.Oiseaux;

import fr.univtln.tbezenger858.Animaux.Animal;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by Tomy Bezenger on 21/02/2017.
 */

public abstract class Oiseau extends Animal {
    public Oiseau() {}

    @Override
    public String toString() {
        return "Oiseau{} " + super.toString();
    }
}

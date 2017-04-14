package fr.univtln.tbezenger858.Animaux.Mammiferes;

import fr.univtln.tbezenger858.Animaux.Animal;

/**
 * Created by Tomy Bezenger on 21/02/2017.
 */


public abstract class Mammifere extends Animal {

    public Mammifere(int id) {
        super(id);
    }
    public Mammifere(){}

    @Override
    public String toString() {
        return "Mammifere{} " + super.toString();
    }
}

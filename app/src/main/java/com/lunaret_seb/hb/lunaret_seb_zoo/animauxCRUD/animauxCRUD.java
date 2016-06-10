package com.lunaret_seb.hb.lunaret_seb_zoo.animauxCRUD;

/**
 * Created by hb on 09/06/2016.
 */

import com.lunaret_seb.hb.lunaret_seb_zoo.animaux.Animaux;

import java.util.ArrayList;
import java.util.Date;


public class AnimauxCRUD {
    static ArrayList<Animaux> animalsList;

    public AnimauxCRUD() {
        animalsList = new ArrayList<Animaux>();
        animalsList.add(new Animaux("Dog", "Canine", new Date(2013 / 05 / 05)));
        animalsList.add(new Animaux("Cat", "Feline", new Date(2015 / 06 / 05)));
        animalsList.add(new Animaux("mouse", "rodent", new Date(2016 / 01 / 05)));

    }

    public static void delete(Animaux animal) {
        delete(animalsList.indexOf(animal));
    }

    private static void delete(int animalId) {
        animalsList.remove(animalId);
    }

    public static void add(Animaux animal) {
        animalsList.add(animal);
    }
    public static void update(Animaux animal, Animaux newAnimal) {
        int index = animalsList.indexOf(animal);
        Animaux currentAnimal = animalsList.get(index);
        currentAnimal.setName(newAnimal.getName());
        currentAnimal.setDateOfBorn(newAnimal.getDateOfBorn());
        currentAnimal.setSpecie(newAnimal.getSpecie());
    }
    public static Animaux retrieve(String name) {
        for (Animaux animal : animalsList) {
            if (animal.getName().equals(name))
                return animal;
        }
        return null;
    }

    public static ArrayList<Animaux> retrieveAll() {
        return animalsList;
    }

}
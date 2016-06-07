package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import java.io.Serializable;
import java.text.DateFormat;

/**
 * Created by hb on 07/06/2016.
 */
public class Animaux implements Serializable{

    private String name;
    private String specie;
    private DateFormat dateOfBorn;

    public Animaux(String name) {
        this.name = name;
    }

    public Animaux(String name, String specie, DateFormat dateOfBorn) {
        this.name = name;
        this.specie = specie;
        this.dateOfBorn = dateOfBorn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateFormat getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(DateFormat dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }


}

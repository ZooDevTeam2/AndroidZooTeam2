package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hb on 07/06/2016.
 */
public class Animaux implements Serializable{

    private String name;
    private String specie;
    private Date dateOfBorn;

    public Animaux(String name) {
        this.name = name;
    }

    public Animaux(String name, String specie, Date dateOfBorn) {
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

    public Date getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(Date dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    @Override
    public String toString() {
        return   name+ " ("+specie+")";
    }
}

package com.lunaret_seb.hb.lunaret_seb_zoo.enclos;

import java.io.Serializable;

/**
 * Created by KEVIN on 07/06/2016.
 */
public class Enclos implements Serializable {

    private String name;
    private int area;
private String description;

    public Enclos(String name, int area, String description) {
        this.name = name;
        this.area = area;
        this.description = description;
    }
    public Enclos(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return  this.getName();
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getArea() {
        return this.area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}

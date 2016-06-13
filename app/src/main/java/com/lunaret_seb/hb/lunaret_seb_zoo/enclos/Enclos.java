package com.lunaret_seb.hb.lunaret_seb_zoo.enclos;

import java.io.Serializable;

public class Enclos implements Serializable{
    private String name;
    private String area;
    private String Description;
    public Enclos(){

    }
    public Enclos(String name, String area, String description) {
        this.name = name;
        this.area = area;
        this.Description = description;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
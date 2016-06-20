package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import java.io.Serializable;

/**
 * Created by KEVIN on 07/06/2016.
 */
public class Stock implements Serializable {

    private String name;
    private int quantities;
    private int maximum;
    private int id;

    public Stock() {
    }

    public Stock(String name, int quantities, int maximum, int id) {
        this.name = name;
        this.quantities = quantities;
        this.maximum = maximum;
        this.id=id;

    }

    @Override
    public String toString() {
        return (name+" ("+quantities+")");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}

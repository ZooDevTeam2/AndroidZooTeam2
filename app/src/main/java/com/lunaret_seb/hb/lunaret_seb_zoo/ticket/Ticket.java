package com.lunaret_seb.hb.lunaret_seb_zoo.ticket;

import java.io.Serializable;

/**
 * Created by Fadul on 08/06/2016.
 */
public class Ticket implements Serializable {

    private String name;
    private double price;

    @Override
    public String toString() {
        return (name);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Constructor
    public Ticket(String name) {
        this.name = name;
    }

}

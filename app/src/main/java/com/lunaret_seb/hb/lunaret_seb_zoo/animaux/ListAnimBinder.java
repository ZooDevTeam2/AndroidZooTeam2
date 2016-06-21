package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import android.app.Service;
import android.os.Binder;

import java.util.ArrayList;

/**
 * Created by hb on 13/06/2016.
 */

public class ListAnimBinder extends Binder {

    private ArrayList<Animaux> listAnimaux;
    private ArrayList<Integer> listAnimauxInt = new ArrayList<>();
    private Service service;

    public ListAnimBinder(Service service) {
        this.service=service;
    }

    public String getToast(){
        return "Mise à jour de la liste";
    }

}
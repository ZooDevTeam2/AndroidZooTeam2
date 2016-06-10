package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.app.Service;
import android.content.ServiceConnection;
import android.os.Binder;

/**
 * Created by KEVIN on 10/06/2016.
 */
public class ListStockBinder extends Binder {

    private Service service;

    public ListStockBinder(Service service) {
        this.service=service;
    }

    public String getToast(){
        return "Et voila, j'ai une méthode de service qui fonctionne";
    }
}

package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.app.Service;
import android.os.Binder;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by KEVIN on 10/06/2016.
 */
public class ListStockBinder extends Binder {

    //private ArrayList<Stock> listStock;
   //private ArrayList<Integer> listStockInt = new ArrayList<>();
    private ListStockIntentService service;

    public ListStockBinder(ListStockIntentService service) {
        this.service=service;    }

    public String getToast(){
        return "Mise à jour de la liste";
    }

    public void getFreshData(StockManager manager) {
        service.getFreshData(manager);
    }

}

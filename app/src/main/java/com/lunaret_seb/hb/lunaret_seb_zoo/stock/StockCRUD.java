package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.ArrayList;

/**
 * Created by KEVIN on 10/06/2016.
 */
public class StockCRUD {

    ArrayList<Stock> listStock;

    public StockCRUD() {

        listStock = new ArrayList<Stock>();
        listStock.add(new Stock("Carotte",300,500,1));
        listStock.add(new Stock("Viande",300,1000,2));
        listStock.add(new Stock("Poisson",120,700,3));
    }

    public void delete(Stock stock) {
        delete(listStock.indexOf(stock));
    }

    private void delete(int id) {
        listStock.remove(id);
    }

    public void add(Stock stock) {
        listStock.add(stock);
    }
    public void update(Stock stock, Stock newStock) {
        int index = listStock.indexOf(stock);
        Stock currentStock = listStock.get(index);
        currentStock.setName(newStock.getName());
        currentStock.setQuantities(newStock.getQuantities());
        currentStock.setMaximum(newStock.getMaximum());
        currentStock.setId(newStock.getId());
}
    public Stock retrieve(int id) {
        for (Stock stock:listStock){
            if(stock.getId()==id)
                return stock;
        }
        return null;
        }

public ArrayList<Stock> retrieveAll(Context context){
    Intent intent = new Intent(context, ListStockIntentService.class);
    context.startService(intent);
    ServiceConnection connection = new ServiceConnection(){
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
    ListStockBinder binder = (ListStockBinder)service;
    listStock = binder.getListStock();
        }
     @Override
     public void onServiceDisconnected(ComponentName name) {

    }
    };
     context.bindService(intent, connection ,0);

    return listStock;
        }
}

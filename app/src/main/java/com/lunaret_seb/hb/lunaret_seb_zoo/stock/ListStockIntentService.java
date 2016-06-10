package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ArrayList;

public class ListStockIntentService extends IntentService {


    private ArrayList<Stock> listStock = new ArrayList<>();


    public ListStockIntentService() {
        super("ListStockIntentService");
    }
    @Override
    public IBinder onBind(Intent intent) {
        IBinder binder= new ListStockBinder(this);
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extra = intent.getExtras();
        ArrayList<Integer> listStockId=  extra.getIntegerArrayList("listStock");

        StockCRUD stockCRUD = new StockCRUD();

        for(int stockId : listStockId){
            listStock.add(stockCRUD.retrieve(stockId));
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}

package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.content.Context;
import android.content.Intent;

import java.util.Collections;
import java.util.List;

/**
 * Created by KEVIN on 20/06/2016.
 */
public class StockManager {

    //private static final String USERNAME = "fpoyer";
    private List<Stock> lastResult = Collections.emptyList();
    private Adapter adapter;

    public void setLastResult(List<Stock> lastResult) {
        this.lastResult = lastResult;
        if (adapter != null) {
            adapter.setNewData(lastResult);
        }
    }

    //public static String getUSERNAME() {
       // return USERNAME;
   // }

   // public void createRepository(String owner, String name) {
       //// TODO
        //throw new UnsupportedOperationException("Not implemented yet");
    //}

    public void refreshList(final Context context, final Adapter adapter) {
        // request fresh list from service ASAP
        Intent intent = new Intent(context, ListStockIntentService.class);
        StockServiceConnection connection = new StockServiceConnection(this, context);
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);

        // In the meantime, return last result
        adapter.setNewData(lastResult);
        this.adapter = adapter;
    }

}

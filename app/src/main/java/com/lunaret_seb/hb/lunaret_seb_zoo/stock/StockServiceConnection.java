package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by KEVIN on 20/06/2016.
 */
public class StockServiceConnection implements ServiceConnection {

    private StockManager manager;
    private final Context context;

    public StockServiceConnection(StockManager manager, Context context) {
        this.manager = manager;
        this.context = context;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        // TODO use binder to request fresh list
        ListStockBinder binder = (ListStockBinder) service;
        binder.getFreshData(manager);
        context.unbindService(this);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

}

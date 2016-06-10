package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ListStockService extends Service {
    public ListStockService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        stopSelfResult(startId);
// For each start request, use a free thread from the pool to start the work
// asynchronously, or add to work queue, etc
// When the task is done, signal it by calling
// stopSelfResult(startId);
// If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}

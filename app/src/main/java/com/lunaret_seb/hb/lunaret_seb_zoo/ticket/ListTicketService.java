package com.lunaret_seb.hb.lunaret_seb_zoo.ticket;

import android.app.IntentService;
//import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ListTicketService extends IntentService {

    public ListTicketService() {
        super("ListTicketService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    public class MyClassImplementIBinder extends Binder {
        public String test() {
            return test();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        //return new MyClassImplementIBinder(intent, this);
        return new MyClassImplementIBinder();
    }
}

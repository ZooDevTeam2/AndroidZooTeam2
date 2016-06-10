package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.lunaret_seb.hb.lunaret_seb_zoo.animauxCRUD.AnimauxCRUD;

import java.util.ArrayList;

public class AnimauxService extends Service {

    private ArrayList<Animaux> listAnimaux = new ArrayList<>();

    public AnimauxService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // handleCommand(intent);


        Bundle extra = intent.getExtras();
        ArrayList<String> listAnimSTR=  extra.getStringArrayList("listAnim");

        AnimauxCRUD animauxCRUD = new AnimauxCRUD();

        for(String animalName : listAnimSTR){
            listAnimaux.add(animauxCRUD.retrieve(animalName));
        }

        Toast.makeText(this, "Toaster from List Animaux" , Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }
    
}

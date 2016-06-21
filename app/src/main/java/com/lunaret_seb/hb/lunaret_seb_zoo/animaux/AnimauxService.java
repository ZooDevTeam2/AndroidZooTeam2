package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.lunaret_seb.hb.lunaret_seb_zoo.animauxCRUD.AnimauxCRUD;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimauxService extends Service {
    //  private IBinder mBinder = new MyBinder();

    private ArrayList<Animaux> listAnimaux = new ArrayList<>();
    public static final String BASE_URL = "http://127.0.0.1:8080";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }


    public AnimauxService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        IBinder binder= new ListAnimauxBinder(this);
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        /*
        Retrofit.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint("http://127.0.0.1:8080"); // Root URL of the server
        RestAdapter build = builder.build();
        AnimauxService animauxService = build.create(AnimauxService.class);
       // Account observable = animauxService.getAccount("12345");
       */
    }


/* TODO
* getRepostoty
* call.enqueue(new Callback<List<Repository>>() -> onResponse -> onFailure */


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // handleCommand(intent);


        Bundle extra = intent.getExtras();
        ArrayList<String> listAnimSTR=  extra.getStringArrayList("listAnim");

        AnimauxCRUD animauxCRUD = new AnimauxCRUD();
        int comptAnim = 0;
        for(String animalName : listAnimSTR){
            listAnimaux.add(animauxCRUD.retrieve(animalName));
            comptAnim += 1;
        }

/*
        AnimauxCRUD animauxCRUD = new AnimauxCRUD();
        final ArrayList<Animaux> listAnimaux = animauxCRUD.retrieveAll();

        for (Animaux animal : listAnimaux) {

            
        }*/

    // TODO SEND THE LIST "listAnimaux" TO THE "ListAnimaux" view

        Toast.makeText(this, "Toaster from List Animaux "+ comptAnim , Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

}

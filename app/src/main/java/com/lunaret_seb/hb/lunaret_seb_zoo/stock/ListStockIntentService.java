package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public class ListStockIntentService extends IntentService {

    public static final String CREATE_ACTION = "create";
    public static final String REPOSITORY_NAME = "Zoo";
    private static final String TAG = ListStockIntentService.class.getSimpleName();
    public static final String PASSWORD = "frct..035";
    public static final String USERNAME = "vayssek";
    private static List<Stock> previousResults = Collections.emptyList();


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

        //for(int stockId : listStockId){
        //   listStock.add(stockCRUD.retrieve(stockId));
       // }
        return START_STICKY;
    }


    @Override
    protected void onHandleIntent(Intent intent) {

    }

    public interface ZooStockClient {
        @GET("/wildfly-lunaret-rs/rest/stocks")
        Call<List<Stock>> stocks();

        @POST("/wildfly-lunaret-rs/rest/add")
        Call<Stock> createStock(@Body Stock stock);
    }

    public static List<Stock> startSyncLocalRepositoryAction(final TextView textView) {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        ZooStockClient client = ServiceGenerator.createService(ZooStockClient.class);

        // Fetch and print a list of the repos of this owner.
        Call<List<Stock>> call =
                client.stocks();

        call.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, retrofit2.Response<List<Stock>> response) {
                if (response.isSuccessful()) {
                    StringBuilder builder = new StringBuilder("List of repos online : \n");
                    List<Stock> stocks = response.body();
                    for (Stock stock : stocks) {
                        builder.append(stock.toString()).append('\n');
                    }
                    textView.setText(builder.toString());
                    previousResults = stocks;
                } else {
                    String msg = "error response code from server: " + response.code();
                    Log.e(TAG, msg);
                    textView.setText(msg);
                    previousResults = Collections.emptyList();
                }
            }

            @Override
            public void onFailure(Call<List<Stock>> call, Throwable t) {
                String msg = t.getMessage();
                Log.e(TAG, "failure : " + msg, t);
                textView.setText(msg);
                previousResults = Collections.emptyList();
            }
        }
        );
        return previousResults;
    }

    public void getFreshData(final StockManager manager) {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        ZooStockClient client = ServiceGenerator.createService(ZooStockClient.class);

        // Fetch and print a list of the repos of this owner.
        Call<List<Stock>> call =
                client.stocks();

        call.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, retrofit2.Response<List<Stock>> response) {
                if (response.isSuccessful()) {
                    manager.setLastResult(response.body());
                } else {
                    String msg = "error response code from server: " + response.code();
                    Log.e(TAG, msg);
                    List<Stock> fakeList = new ArrayList<Stock>();
                    Stock stock1 = new Stock();
                    stock1.setName("Fake Error Repository!!");
                    fakeList.add(stock1);
                    manager.setLastResult(fakeList);
                }
            }

            @Override
            public void onFailure(Call<List<Stock>> call, Throwable t) {
                String msg = t.getMessage();
                Log.e(TAG, "failure : " +msg, t);
                List<Stock> fakeList = new ArrayList<Stock>();
                Stock stock1 = new Stock();
                stock1.setName("Fake Failure Repository!!");
                fakeList.add(stock1);
                manager.setLastResult(fakeList);
            }
        }
        );
    }
    public static class ServiceGenerator {

        public static final String API_BASE_URL = "http://10.0.2.2:8080";
        private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        private static Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        public static <S> S createService(Class<S> serviceClass) {
            Retrofit retrofit = builder.client(httpClient.build()).build();
            return retrofit.create(serviceClass);
        }

    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            httpClient.addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .header("Authorization", basic)
                                .header("Accept", "application/json")
                                .method(original.method(), original.body());

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                });
            }

            OkHttpClient client = httpClient.build();
            Retrofit retrofit = builder.client(client).build();
            return retrofit.create(serviceClass);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}

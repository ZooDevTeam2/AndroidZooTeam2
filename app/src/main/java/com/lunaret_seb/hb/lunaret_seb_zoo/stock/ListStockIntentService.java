package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.app.IntentService;
import android.content.Context;
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
import retrofit2.Response;
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

    public static void startCreateRepositoryAction(Context context, String repositoryName, TextView textView) {
        Intent intent = new Intent(context, ListStockIntentService.class);
        intent.setAction(CREATE_ACTION);
        intent.putExtra(REPOSITORY_NAME, repositoryName);
        view = textView;
        context.startService(intent);
    }

    public static List<Stock> startSyncLocalRepositoryAction(String username, final TextView textView) {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        GitHubClient client = ServiceGenerator.createService(GitHubClient.class);

        // Fetch and print a list of the repos of this owner.
        Call<List<Stock>> call =
                client.repositories(username);

        call.enqueue(new Callback<List<Stock>>() {
                         @Override
                         public void onResponse(Call<List<Stock>> call, retrofit2.Response<List<Stock>> response) {
                             if (response.isSuccessful()) {
                                 StringBuilder builder = new StringBuilder("List of repos online : \n");
                                 List<Stock> repos = response.body();
                                 for (Stock repo :
                                         repos) {
                                     builder.append(repo.getName()).append('\n');
                                 }
                                 textView.setText(builder.toString());
                                 previousResults = repos;
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
                             Log.e(TAG, "failure : " +msg, t);
                             textView.setText(msg);
                             previousResults = Collections.emptyList();
                         }
                     }
        );
        return previousResults;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (CREATE_ACTION.equals(action)) {
                final String param1 = intent.getStringExtra(REPOSITORY_NAME);
                final TextView textView = view;
                handleCreateRepository(param1, textView);
            } else {
                Log.e(TAG, "Error: unrecognized Action : " + action);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleCreateRepository( final TextView textview) {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        GitHubClient client = ServiceGenerator.createService(GitHubClient.class, USERNAME, PASSWORD);
        Stock repository = new Stock();
        repository.toString();
        final Call<Stock> call = client.createRepository(repository);
        try {
            final Response<Stock> execute = call.execute();
            textview.post(new Runnable() {
                @Override
                public void run() {
                    textview.setText(Integer.toString(execute.code()));

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            textview.post(new Runnable() {
                @Override
                public void run() {
                    textview.setText("Error!");
                }
            });
        }
    }


    public interface GitHubClient {
        @GET("/users/{owner}/repos")
        Call<List<Stock>> repositories(
                //@Path("owner") String owner
        );

        @POST("user/repos")
        Call<Stock> createRepository(@Body Stock repository);
    }

    public static class ServiceGenerator {

        public static final String API_BASE_URL = "http://10.0.2.2";

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
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();

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

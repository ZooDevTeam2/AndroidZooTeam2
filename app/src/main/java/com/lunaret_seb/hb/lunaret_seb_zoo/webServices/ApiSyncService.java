package com.lunaret_seb.hb.lunaret_seb_zoo.webServices;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
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


/**
 * This service provides method to keep local list of repositories in sync with the one on Github...
 */
public class ApiSyncService extends IntentService {

    public static final String CREATE_ACTION = "create";
    public static final String REPOSITORY_NAME = "name";
    private static final String TAG = ApiSyncService.class.getSimpleName();
    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";
    private static List<Repository> previousResults = Collections.emptyList();

    /*Provides a name for the started thread, may be usefull in debug*/
    public ApiSyncService() {
        super(ApiSyncService.class.getSimpleName());
    }

    private static TextView view;
    /**
     * Starts this service to perform create repository action with the given name. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startCreateRepositoryAction(Context context, String repositoryName, TextView textView) {
        Intent intent = new Intent(context, ApiSyncService.class);
        intent.setAction(CREATE_ACTION);
        intent.putExtra(REPOSITORY_NAME, repositoryName);
        view = textView;
        context.startService(intent);
    }

    public static List<Repository> startSyncLocalRepositoryAction(String username, final TextView textView) {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        GitHubClient client = ServiceGenerator.createService(GitHubClient.class);

        // Fetch and print a list of the repos of this owner.
        Call<List<Repository>> call =
                client.repositories(username);

        call.enqueue(new Callback<List<Repository>>() {
                         @Override
                         public void onResponse(Call<List<Repository>> call, retrofit2.Response<List<Repository>> response) {
                             if (response.isSuccessful()) {
                                 StringBuilder builder = new StringBuilder("List of repos online : \n");
                                 List<Repository> repos = response.body();
                                 for (Repository repo :
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
                         public void onFailure(Call<List<Repository>> call, Throwable t) {
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
    private void handleCreateRepository(String repositoryName, final TextView textview) {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        GitHubClient client = ServiceGenerator.createService(GitHubClient.class, USERNAME, PASSWORD);
        Repository repository = new Repository();
        repository.setName(repositoryName);
        final Call<Repository> call = client.createRepository(repository);
        try {
            final Response<Repository> execute = call.execute();
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
        Call<List<Repository>> repositories(
                @Path("owner") String owner
        );

        @POST("user/repos")
        Call<Repository> createRepository(@Body Repository repository);
    }

    public static class ServiceGenerator {

        public static final String API_BASE_URL = "https://api.github.com";

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

}

package com.example.habibdurodola.takehome.activity;
import android.app.Application;
import com.example.habibdurodola.takehome.network.ApiClient;


public class MainApplication extends Application {
    private static MainApplication instance;
    private ApiClient apiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

    }

    public static synchronized MainApplication getInstance() {
        return instance;
    }



    public ApiClient getApiClientInstance() {
        if (apiClient == null) {
            //apiClient = new ApiClient(getApplicationContext());
        }
        return apiClient;
    }


}

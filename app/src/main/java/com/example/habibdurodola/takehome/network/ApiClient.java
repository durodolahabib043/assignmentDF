package com.example.habibdurodola.takehome.network;

import android.content.Context;

import com.example.habibdurodola.takehome.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.concurrent.TimeUnit;


import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final long TIMEOUT = 40L;
    private PublicCallInterface publicService;

    public ApiClient() {
        this.publicService = getRestAdapter(BuildConfig.base, new PublicCallInterceptor()).create(PublicCallInterface.class);
    }

    public PublicCallInterface getPublicApiCall() {
        return publicService;
    }

    private Retrofit getRestAdapter(String url, Interceptor interceptor) {
        Gson gson = new GsonBuilder().create();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(url);
        builder.client(okHttpClient(interceptor));
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));

        return builder.build();
    }

    private OkHttpClient okHttpClient(Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);

        builder.addInterceptor(interceptor);
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(httpLoggingInterceptor);
        builder.protocols(Collections.singletonList(Protocol.HTTP_1_1));
        return builder.build();
    }
}

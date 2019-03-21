package com.example.habibdurodola.takehome.network;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Locale;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class PublicCallInterceptor implements Interceptor {
    PublicCallInterceptor() {
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder().build();
        request = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry()).url(url).build();
        return chain.proceed(request);
    }
}

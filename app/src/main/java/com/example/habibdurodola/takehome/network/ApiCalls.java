package com.example.habibdurodola.takehome.network;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.habibdurodola.takehome.model.github.Github;

import java.io.IOException;
import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ApiCalls {

    private static ApiCalls instance;
    private ApiClient apiClient;
    private final int SUCCESS = 200;

    private ApiCalls() {
        instance = this;
       // apiClient = MainApplication.getInstance().getApiClientInstance();
       // if (apiClient == null) {
            apiClient = new ApiClient();
       // }
       // return apiClient;
    }


    public static synchronized ApiCalls getInstance() {
        if (instance == null) {
            instance = new ApiCalls();
        }
        return instance;
    }


    public Disposable getGitubDetails(String users, @NonNull final ResponseListener<Github> callback) {
        return apiClient.getPublicApiCall().getRepos(users)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ApiResponseCallbackWrapper<Response<List<Github>>>() {


                    @Override
                    protected void onSuccess(Response<List<Github>> user) {
                        Log.d("errorCOde", ""+user.code());
                        if (user.code() == SUCCESS) {
                            callback.onSuccess(user.body());

                        } else {
                            callback.onResponseError(new Throwable());
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        if (error instanceof IOException) {
                            callback.onNoConnection(error);
                        } else {
                            Log.d(error.getLocalizedMessage(),error.getMessage());
                            callback.onResponseError(error);
                        }
                    }
                });
    }

//    public Disposable getBrands(@NonNull final ResponseListener<Node> callback) {
//        return apiClient.getPublicApiCall().getBrands()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new ApiResponseCallbackWrapper<Response<Node>>() {
//                    @Override
//                    protected void onSuccess(Response<Node> user) {
//                        if (user.code() == SUCCESS) {
//                            callback.onSuccess(user.body());
//
//                        } else {
//                            callback.onResponseError(new Throwable());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable error) {
//                        if (error instanceof IOException) {
//                            callback.onNoConnection(error);
//                        } else {
//                            callback.onResponseError(error);
//                        }
//                    }
//                });
//    }


}

package com.example.habibdurodola.takehome.network;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class ApiResponseCallbackWrapper<T> extends DisposableObserver<T> {
    private final static String TAG = "rxjava callback wrapper";

    ApiResponseCallbackWrapper() {
    }

    protected abstract void onSuccess(T t);

    @Override
    public void onNext(T t) {
        onSuccess(t);

    }

    @Override
    public void onError(Throwable error) {
        try {
            // We had non-2xx http error
            if (error instanceof HttpException) {
                HttpException httpException = (HttpException) error;
                Response response = httpException.response();
                String errorMsg = getErrorMessage(response.errorBody());
                Log.i(TAG, errorMsg + " / " + error.getClass());
            }
            // A network error happened
            if (error instanceof IOException) {
                Log.i(TAG, error.getMessage() + " / " + error.getClass());
            }

            Log.i(TAG, error.getMessage() + " / " + error.getClass());
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        }
    }

    @Override
    public void onComplete() { }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}

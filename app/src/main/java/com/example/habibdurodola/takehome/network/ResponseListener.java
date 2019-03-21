package com.example.habibdurodola.takehome.network;

import java.util.List;

public interface ResponseListener<T> {
    void onSuccess(List<T> response);
    void onNoConnection(Throwable t);
    void onResponseError(Throwable t);
}

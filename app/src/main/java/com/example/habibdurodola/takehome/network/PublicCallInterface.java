package com.example.habibdurodola.takehome.network;


import com.example.habibdurodola.takehome.model.github.Github;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PublicCallInterface {
//https://api.myjson.com/bins/og746
    //  @GET("users/{user}/repos")
    //  Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("/users/{user}/repos")
    Observable<Response<List<Github>>> getRepos(@Path("user") String user);

}

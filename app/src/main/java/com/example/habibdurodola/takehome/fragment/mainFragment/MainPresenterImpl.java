package com.example.habibdurodola.takehome.fragment.mainFragment;


import android.util.Log;

import com.example.habibdurodola.takehome.base.BasePresenter;
import com.example.habibdurodola.takehome.model.github.Github;
import com.example.habibdurodola.takehome.network.ApiCalls;
import com.example.habibdurodola.takehome.network.ResponseListener;

import java.util.List;

public class MainPresenterImpl extends BasePresenter implements mainPresenter {
    MainView view;

    public MainPresenterImpl(MainView view ) {
        this.view = view;
    }

    @Override
    public void getBrands(String user) {
        Log.d("MainPresenterImpl", "MainPresenterImpl");

        compositeDisposable.add(ApiCalls.getInstance().getGitubDetails(user, new ResponseListener<Github>() {


            @Override
            public void onSuccess(List<Github> response) {
                view.displayBrands(response);

            }

            @Override
            public void onNoConnection(Throwable t) {
             //   view.toast();
            }

            @Override
            public void onResponseError(Throwable t) {
               // view.toast();
                Log.d("MainPresenterImpl", "error");
            }
        }));


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

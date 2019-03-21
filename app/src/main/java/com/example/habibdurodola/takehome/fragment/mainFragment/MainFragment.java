package com.example.habibdurodola.takehome.fragment.mainFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.habibdurodola.takehome.R;

import com.example.habibdurodola.takehome.activity.MainApplication;
import com.example.habibdurodola.takehome.adapter.GitHubAdapterAdapter;
import com.example.habibdurodola.takehome.base.BaseFragment;
import com.example.habibdurodola.takehome.model.github.Github;
import com.example.habibdurodola.takehome.model.github.Owner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends BaseFragment implements MainView {

    private mainPresenter mainPresenter;
    private EditText inputEditText;
    private Button clickMe;
    private ImageView displayPicture;


    private RecyclerView recyclerView;
    private GitHubAdapterAdapter adapter;


    public MainFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mainfragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        mainPresenter = new MainPresenterImpl(this);

        inputEditText = view.findViewById(R.id.inputEdittext);
        clickMe = view.findViewById(R.id.clickMe);
        recyclerView = view.findViewById(R.id.recylerview);
        displayPicture = view.findViewById(R.id.displayPicture);
        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPresenter.getBrands(inputEditText.getText().toString());
               hideKeyboard(getActivity());

            }
        });

    }


    @Override
    public void displayBrands(List<Github> response) {

        Picasso.get().load(response.get(0).getOwner().getAvatarUrl()).into(displayPicture);

        adapter = new GitHubAdapterAdapter(response ,getActivity() , (MainFragment) MainFragment.newInstance());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainApplication.getInstance());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}

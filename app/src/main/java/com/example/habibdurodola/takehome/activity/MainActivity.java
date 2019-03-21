package com.example.habibdurodola.takehome.activity;


import android.os.Bundle;

import com.example.habibdurodola.takehome.R;
import com.example.habibdurodola.takehome.base.BaseActivity;
import com.example.habibdurodola.takehome.fragment.mainFragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachFragment(MainFragment.newInstance());
    }
}

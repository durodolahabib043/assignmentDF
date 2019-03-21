package com.example.habibdurodola.takehome.customdialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.habibdurodola.takehome.R;


public class MyCustomDialogFragment extends DialogFragment {
 private TextView likesId, likesIdNN ;
  private   Bundle args ;

    public MyCustomDialogFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_custom_dialog, container, false);
        args = getArguments();

        init(view);

        return  view ;
    }

    private void  init(View view){

        likesId = view.findViewById(R.id.likesId);
        likesIdNN = view.findViewById(R.id.likesIdNN);

        if (args !=null){
            likesId.setText(args.getString("keyId"));
            likesIdNN.setText(args.getString("likesId"));
        }

    }


}

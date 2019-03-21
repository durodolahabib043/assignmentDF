package com.example.habibdurodola.takehome.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.habibdurodola.takehome.customdialog.MyCustomDialogFragment;
import com.example.habibdurodola.takehome.fragment.mainFragment.MainFragment;


public abstract class BaseFragment extends Fragment {
    public void switchFragment(FragmentActivity fragmentActivity , String fragName , String likes ) {
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        Fragment prev = fragmentActivity.getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        Bundle bundle = new Bundle();
        bundle.putString("keyId", fragName);
        bundle.putString("likesId", likes);
        MyCustomDialogFragment dialogFragment = new MyCustomDialogFragment();
        dialogFragment.setArguments(bundle);

        dialogFragment.show(ft, "dialog");

    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

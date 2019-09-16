package com.example.retailpos.Helper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.retailpos.R;

public class Utils {

    public static void replaceFragment(Context context,Fragment fragment){
        Activity activity=(Activity)context;
        FragmentTransaction ft=activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.main_container,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void replaceFragment(Context context, Fragment fragment, Bundle bundle){
        Activity activity=(Activity)context;
        FragmentTransaction ft=activity.getFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        ft.replace(R.id.main_container,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void disableKeyboard(final EditText editText){
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int inType = editText.getInputType(); // backup the input type
                editText.setInputType(InputType.TYPE_NULL); // disable soft input
                editText.onTouchEvent(motionEvent); // call native handler
                editText.setInputType(inType); // restore input type
                return true; // consume touch even
            }
        });
    }
}

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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {

    public static void replaceFragment(Context context,Fragment fragment,String tag){
        Activity activity=(Activity)context;
        FragmentTransaction ft=activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.main_container,fragment,tag);
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



    public static void copyDataBase(Context myContext) throws IOException
    {
        String package_name=myContext.getPackageName();
        String DB_PATH = "/data/data/"+package_name+"/databases/";
        String DB_NAME = "sales_inventory_db.db";

        InputStream mInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[2024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
        System.out.println("database copied");
    }
}

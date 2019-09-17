package com.example.retailpos.fragment.Inventory;


import android.app.Fragment;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retailpos.Helper.Utils;
import com.example.retailpos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEditFragment extends Fragment implements View.OnClickListener {
    View rootView;
    EditText edt_name,edt_generic,edt_batch_no,edt_cost,edt_price,edt_min,edt_max;
    Button btn_add,btn_clear;

    public AddEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add_edit, container, false);
        edt_name=rootView.findViewById(R.id.edt_name);
        edt_generic=rootView.findViewById(R.id.edt_generic);
        edt_batch_no=rootView.findViewById(R.id.edt_batch_no);
        edt_cost=rootView.findViewById(R.id.edt_cost);
        edt_price=rootView.findViewById(R.id.edt_price);
        edt_min=rootView.findViewById(R.id.edt_min);
        edt_max=rootView.findViewById(R.id.edt_max);
        btn_add=rootView.findViewById(R.id.btn_add);
        btn_clear=rootView.findViewById(R.id.btn_clear);

        Utils.disableKeyboard(edt_batch_no);
        Utils.disableKeyboard(edt_cost);
        Utils.disableKeyboard(edt_generic);
        Utils.disableKeyboard(edt_max);
        Utils.disableKeyboard(edt_min);
        Utils.disableKeyboard(edt_name);
        Utils.disableKeyboard(edt_price);


        btn_clear.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        int type=-1;
        if(savedInstanceState!=null){
            type=savedInstanceState.getInt("Type");
        }
        if(type==0){


        }
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_add){
            if(!TextUtils.isEmpty(edt_name.getText()) && !TextUtils.isEmpty(edt_generic.getText())
                    && !TextUtils.isEmpty(edt_batch_no.getText()) && !TextUtils.isEmpty(edt_cost.getText())
                    && !TextUtils.isEmpty(edt_price.getText()) && !TextUtils.isEmpty(edt_min.getText())
                    && !TextUtils.isEmpty(edt_max.getText())){
                Toast.makeText(rootView.getContext(), "Saved...", Toast.LENGTH_SHORT).show();
                edt_max.setText("");
                edt_min.setText("");
                edt_price.setText("");
                edt_cost.setText("");
                edt_batch_no.setText("");
                edt_generic.setText("");
                edt_name.setText("");
            }else{
                Toast.makeText(rootView.getContext(), "Empty field not allowed...", Toast.LENGTH_SHORT).show();
            }
        }else{
            edt_max.setText("");
            edt_min.setText("");
            edt_price.setText("");
            edt_cost.setText("");
            edt_batch_no.setText("");
            edt_generic.setText("");
            edt_name.setText("");
        }
    }

    public void passDataToFragment(final String key) {

        if(edt_name.hasFocus()){
            int length = edt_name.getText().length();
            if(key.equals("backspace")){

                if(length>0) {
                    edt_name.getText().delete(length-1,length);
                }
            }else {
                edt_name.append(key);
            }
        }
        if(edt_generic.hasFocus()){
            int length = edt_generic.getText().length();
            if(key.equals("backspace")){

                if(length>0) {
                    edt_generic.getText().delete(length-1,length);
                }
            }else {
                edt_generic.append(key);
            }
        }
        if(edt_batch_no.hasFocus()){
            int length = edt_batch_no.getText().length();
            if(key.equals("backspace")){

                if(length>0) {
                    edt_batch_no.getText().delete(length-1,length);
                }
            }else {
                edt_batch_no.append(key);
            }
        }
        if(edt_cost.hasFocus()){
            int length = edt_cost.getText().length();
            if(key.equals("backspace")){

                if(length>0) {
                    edt_cost.getText().delete(length-1,length);
                }
            }else {
                edt_cost.append(key);
            }
        }
        if(edt_price.hasFocus()){
            int length = edt_price.getText().length();
            if(key.equals("backspace")){

                if(length>0) {
                    edt_price.getText().delete(length-1,length);
                }
            }else {
                edt_price.append(key);
            }
        }
        if(edt_min.hasFocus()){
            int length = edt_min.getText().length();
            if(key.equals("backspace")){

                if(length>0) {
                    edt_min.getText().delete(length-1,length);
                }
            }else {
                edt_min.append(key);
            }
        }
        if(edt_max.hasFocus()){
            int length = edt_max.getText().length();
            if(key.equals("backspace")){

                if(length>0) {
                    edt_max.getText().delete(length-1,length);
                }
            }else {
                edt_max.append(key);
            }
        }
    }
}

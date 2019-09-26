package com.example.retailpos.fragment.Sales;


import android.app.Fragment;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retailpos.Helper.Utils;
import com.example.retailpos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment extends Fragment implements View.OnClickListener {
    View rootView;
    EditText edt_customer;
    EditText edt_date;
    EditText edt_cashier;
    EditText edt_invoice;
    EditText edt_medicine_name;
    EditText edt_discount;
    TextView tv_no_items;
    Button btn_pay;
    RelativeLayout relativeLayout;
    boolean isCaptial = false;
    RelativeLayout relative_field;
    public SalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_sales, container, false);
        tv_no_items=rootView.findViewById(R.id.tv_no_items);
        edt_customer=rootView.findViewById(R.id.edt_customer);
        edt_date=rootView.findViewById(R.id.edt_date);
        edt_cashier=rootView.findViewById(R.id.edt_cashier);
        edt_invoice=rootView.findViewById(R.id.edt_invoice);
        edt_medicine_name=rootView.findViewById(R.id.edt_medicine_name);
        edt_discount=rootView.findViewById(R.id.edt_discount);
        btn_pay=rootView.findViewById(R.id.btn_pay);
        relativeLayout=rootView.findViewById(R.id.relative_alphabet);
        relative_field=rootView.findViewById(R.id.relative_field);

        Utils.disableKeyboard(edt_customer);
        Utils.disableKeyboard(edt_date);
        Utils.disableKeyboard(edt_cashier);
        Utils.disableKeyboard(edt_invoice);
        Utils.disableKeyboard(edt_medicine_name);
        Utils.disableKeyboard(edt_customer);
        Utils.disableKeyboard(edt_discount);
        btn_pay.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_pay){

        }
    }

    public boolean upperCase(boolean isUpper){
        if(isUpper){
            for(int i=0;i<relativeLayout.getChildCount();i++){
                LinearLayout layout=(LinearLayout)relativeLayout.getChildAt(i);
                for(int j=0;j<layout.getChildCount();j++){
                    Button btn=(Button)layout.getChildAt(j);
                    btn.setAllCaps(true);
                }
            }
        }else {
            for(int i=0;i<relativeLayout.getChildCount();i++){
                LinearLayout layout=(LinearLayout)relativeLayout.getChildAt(i);
                for(int j=0;j<layout.getChildCount();j++){
                    Button btn=(Button)layout.getChildAt(j);
                    btn.setAllCaps(false);
                }
            }
        }

        return isUpper;
    }
    public void passDataToFragment(View view) {
        Button btn=(Button)view;
        String str;
        if(isCaptial){
            str=btn.getText().toString().toUpperCase();
        }else{
            str=btn.getText().toString().toLowerCase();
        }

        if(view.getId()==R.id.btn_return){
            if(edt_customer.hasFocus()){
                edt_medicine_name.requestFocus();
            }else if(edt_medicine_name.hasFocus()){
                edt_discount.requestFocus();
            }

        }

        if(view.getId() ==R.id.btn_cap){
            isCaptial=!isCaptial;
            upperCase(isCaptial);
        }

        for(int i=0;i<relative_field.getChildCount();i++){
                if(relative_field.getChildAt(i) instanceof LinearLayout){
                    LinearLayout layout=(LinearLayout)relative_field.getChildAt(i);
                    for(int j=0;j<layout.getChildCount();j++){
                        Log.e("Linear----------->", String.valueOf(layout.getChildAt(j)));
                        if(layout.getChildAt(j)instanceof EditText){
                            EditText editText=(EditText)layout.getChildAt(j);

                            if(editText.hasFocus()){
                                int length=editText.getText().length();
                                if(view.getId()==R.id.btn_backspace){
                                    if(length>0) {
                                        editText.getText().delete(length - 1, length);
                                    }
                                }if(view.getId()==R.id.btn_space){
                                    editText.append(" ");
                                } else{
                                    editText.append(str);
                                }
                            }
                        }
                    }

                }

        }


    }
}

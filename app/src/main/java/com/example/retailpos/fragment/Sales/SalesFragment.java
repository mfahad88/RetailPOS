package com.example.retailpos.fragment.Sales;


import android.app.Fragment;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retailpos.Helper.Utils;
import com.example.retailpos.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment extends Fragment implements View.OnClickListener {
    View rootView;
    EditText edt_customer;
    EditText edt_date;
    EditText edt_cashier;
    EditText edt_invoice;
    AutoCompleteTextView edt_medicine_name;
    EditText edt_discount;
    TextView tv_no_items;
    Button btn_pay;
    RelativeLayout relativeLayout;
    boolean isCaptial = false;
    RelativeLayout relative_field;
    SimpleDateFormat formatter;
    public SalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_sales, container, false);
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        tv_no_items=rootView.findViewById(R.id.tv_no_items);
        edt_customer=rootView.findViewById(R.id.edt_customer);
        edt_date=rootView.findViewById(R.id.edt_date);
        edt_cashier=rootView.findViewById(R.id.edt_cashier);
        edt_invoice=rootView.findViewById(R.id.edt_invoice);
        edt_medicine_name=rootView.findViewById(R.id.edt_medicine_name);
        edt_discount=rootView.findViewById(R.id.edt_discount);
        btn_pay=rootView.findViewById(R.id.btn_pay);
        relative_field=rootView.findViewById(R.id.relative_field);

//        Utils.disableKeyboard(edt_customer);
//        Utils.disableKeyboard(edt_date);
//        Utils.disableKeyboard(edt_cashier);
//        Utils.disableKeyboard(edt_invoice);
//        Utils.disableKeyboard(edt_medicine_name);
//        Utils.disableKeyboard(edt_customer);
//        Utils.disableKeyboard(edt_discount);
        btn_pay.setOnClickListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    try {
                        edt_date.post(new Runnable() {
                            @Override
                            public void run() {
                                edt_date.setText(formatter.format(new Date()));
                            }
                        });


                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_pay){

        }
    }



}

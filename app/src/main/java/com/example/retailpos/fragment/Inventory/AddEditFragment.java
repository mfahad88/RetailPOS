package com.example.retailpos.fragment.Inventory;


import android.app.Fragment;
import android.os.Bundle;


import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retailpos.Helper.Utils;
import com.example.retailpos.R;
import com.example.retailpos.database.DbHelper;
import com.example.retailpos.model.BatchBean;
import com.example.retailpos.model.InventoryBean;
import com.example.retailpos.model.ProductBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEditFragment extends Fragment implements View.OnClickListener {
    View rootView;
    EditText edt_name,edt_generic,edt_batch_no,edt_cost,edt_price,edt_min,edt_max,edt_expiry_date,edt_qty;
    Button btn_add,btn_clear;
    RelativeLayout relativeLayout;
    boolean isCaptial=false;
    private RelativeLayout relative_field;
    private DbHelper dbHelper;
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
        edt_expiry_date=rootView.findViewById(R.id.edt_expiry_date);
        edt_qty=rootView.findViewById(R.id.edt_qty);
        btn_add=rootView.findViewById(R.id.btn_add);
        btn_clear=rootView.findViewById(R.id.btn_clear);
        relativeLayout=rootView.findViewById(R.id.relative_alphabet);
        relative_field=rootView.findViewById(R.id.relative_field);
        dbHelper=new DbHelper(rootView.getContext());
        Utils.disableKeyboard(edt_batch_no);
        Utils.disableKeyboard(edt_cost);
        Utils.disableKeyboard(edt_generic);
        Utils.disableKeyboard(edt_max);
        Utils.disableKeyboard(edt_min);
        Utils.disableKeyboard(edt_name);
        Utils.disableKeyboard(edt_price);
        Utils.disableKeyboard(edt_expiry_date);
        Utils.disableKeyboard(edt_qty);

        edt_name.requestFocus();

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
                    && !TextUtils.isEmpty(edt_max.getText()) &&!TextUtils.isEmpty(edt_expiry_date.getText())){
                int product_id = -1;
                int batch_id;
                BatchBean batchBean;
                InventoryBean inventoryBean;
                ProductBean bean=new ProductBean(0,edt_generic.getText().toString(),edt_name.getText().toString(),edt_cost.getText().toString(),
                        edt_price.getText().toString(),Integer.parseInt(edt_min.getText().toString()),Integer.parseInt(edt_max.getText().toString()));
                if(dbHelper.selectByNameProduct(bean).size()>0){
                    product_id=dbHelper.updateProduct(bean);
                }else{
                    product_id = (int) dbHelper.insertProduct(bean);
                }
                batchBean = new BatchBean(0, product_id, edt_batch_no.getText().toString(), edt_expiry_date.getText().toString(), Integer.parseInt(edt_qty.getText().toString()));
                if(dbHelper.selectByProductIdBatch(batchBean).size()>0) {
                    batch_id = dbHelper.updateBatch(batchBean);
                }else{
                    batch_id = (int) dbHelper.insertBatch(batchBean);
                }
                inventoryBean=new InventoryBean(0,product_id,Integer.parseInt(edt_qty.getText().toString()));

                if(dbHelper.getByIdInventory(inventoryBean).size()>0){
                    dbHelper.updateInventory(inventoryBean);
                }else{
                    dbHelper.insertInventory(inventoryBean);
                }


                if(product_id!=-1 && batch_id!=-1){
                    Toast.makeText(rootView.getContext(), "Saved...", Toast.LENGTH_SHORT).show();
                    edt_max.setText("");
                    edt_min.setText("");
                    edt_price.setText("");
                    edt_cost.setText("");
                    edt_batch_no.setText("");
                    edt_generic.setText("");
                    edt_name.setText("");
                    edt_qty.setText("");
                    edt_expiry_date.setText("");
                }else{
                    Toast.makeText(rootView.getContext(), "Something went wrong please try again...", Toast.LENGTH_SHORT).show();
                }
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
            edt_qty.setText("");
            edt_expiry_date.setText("");
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
    public void passDataToFragment(final View view) {
        Button btn=(Button)view;
        String str;
        if(isCaptial){
            str=btn.getText().toString().toUpperCase();
        }else{
            str=btn.getText().toString().toLowerCase();
        }
        if(view.getId()==R.id.btn_return){
            if(edt_name.hasFocus()){
                edt_generic.requestFocus();
            }else if(edt_generic.hasFocus()){
                edt_batch_no.requestFocus();
            }else if(edt_batch_no.hasFocus()){
                edt_cost.requestFocus();
            }else if(edt_cost.hasFocus()){
                edt_price.requestFocus();
            }else if(edt_price.hasFocus()){
                edt_min.requestFocus();
            }else if(edt_min.hasFocus()){
                edt_max.requestFocus();
            }else if(edt_max.hasFocus()){
                edt_qty.requestFocus();
            }else if(edt_qty.hasFocus()){
                edt_expiry_date.requestFocus();
            }

        }

        if(view.getId() ==R.id.btn_cap){
            isCaptial=!isCaptial;
            upperCase(isCaptial);
        }



        for(int j=0;j<relative_field.getChildCount();j++){
            LinearLayout layout=(LinearLayout)relative_field.getChildAt(j);
//                Log.e("Relative--->", String.valueOf(layout.getChildAt(j)));
            for(int k=0;k<layout.getChildCount();k++){
                View v=(View)layout.getChildAt(k);
                EditText editText;
                TextView textView;
                if(v instanceof EditText) {
                    editText=(EditText)layout.getChildAt(k);
                    if(editText.hasFocus()){
                        int length=editText.getText().length();
                        if(view.getId()==R.id.btn_backspace){

                            if(length>0) {
                                editText.getText().delete(length - 1, length);
                            }
                        }if (view.getId()==R.id.btn_space){
                            editText.append(" ");
                        } else {
                            editText.append(str);
                        }

                    }

                }else if(v instanceof TextView){
                    textView=(TextView)layout.getChildAt(k);
                }
            }
        }
    }
}

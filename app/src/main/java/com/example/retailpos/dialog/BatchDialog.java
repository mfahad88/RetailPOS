package com.example.retailpos.dialog;


import android.app.DialogFragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retailpos.R;
import com.example.retailpos.adapter.BatchAdapter;
import com.example.retailpos.database.DbHelper;
import com.example.retailpos.interfaces.InterfaceBatch;

public class BatchDialog extends DialogFragment {
    View rootView;
    ListView listview_batch;
    DbHelper dbHelper;
    InterfaceBatch interfaceBatch;
    public BatchDialog() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(1000,1000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_batch_dialog, container, false);
        int product_id=getArguments().getInt("product_id");
        listview_batch=rootView.findViewById(R.id.listview_batch);
        dbHelper=new DbHelper(rootView.getContext());

        interfaceBatch=new InterfaceBatch() {
            @Override
            public void isBatch(boolean isDeleted) {
                dismiss();
                if(isDeleted){
                    Toast.makeText(rootView.getContext(), "Record deleted...", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(rootView.getContext(), "Something went wrong try again...", Toast.LENGTH_SHORT).show();
                }
            }
        };
        BatchAdapter adapter=new BatchAdapter(rootView.getContext(),R.layout.batch_adapter,dbHelper.getAllBatch(product_id),dbHelper,interfaceBatch);
        listview_batch.setAdapter(adapter);
        return rootView;
    }

}

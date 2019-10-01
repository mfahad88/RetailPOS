package com.example.retailpos.fragment.Inventory;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retailpos.Helper.Utils;
import com.example.retailpos.R;
import com.example.retailpos.adapter.InventoryAdapter;
import com.example.retailpos.database.DbHelper;
import com.example.retailpos.interfaces.InterfaceInventory;
import com.example.retailpos.interfaces.InterfaceKeyboard;


/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends Fragment implements View.OnClickListener {
    View rootView;
    ListView listview_inventory;
    Button btn_add;
    AddEditFragment fragment;
    DbHelper dbHelper;
    InterfaceInventory interfaceInventory;
    InventoryAdapter adapter;

    public InventoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_inventory, container, false);
        init();
        adapter = new InventoryAdapter(rootView.getContext(),R.layout.inventory_adapter,dbHelper.getAllProductInventory(),interfaceInventory,dbHelper);
        listview_inventory.setAdapter(adapter);
        btn_add.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_add){
            Bundle bundle=new Bundle();
            bundle.putInt("Type",0); //Add
            Utils.replaceFragment(rootView.getContext(),fragment,bundle);
        }
    }

    public void init(){
        listview_inventory=rootView.findViewById(R.id.listview_inventory);
        btn_add=rootView.findViewById(R.id.btn_add);
        fragment=new AddEditFragment();
        dbHelper=new DbHelper(rootView.getContext());
        interfaceInventory=new InterfaceInventory() {
            @Override
            public void isInventory(boolean isdeleted) {

                if(isdeleted){
                    adapter.clear();
                    adapter.addAll(dbHelper.getAllProductInventory());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(rootView.getContext(), "Record deleted...", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(rootView.getContext(), "Something went wrong try again please...", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }



}




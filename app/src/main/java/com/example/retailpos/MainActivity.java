package com.example.retailpos;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;

import com.example.retailpos.Helper.Utils;
import com.example.retailpos.database.DbHelper;
import com.example.retailpos.fragment.Inventory.InventoryFragment;
import com.example.retailpos.fragment.Order.OrderFragment;
import com.example.retailpos.fragment.Sales.SalesFragment;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_inventory;
    TextView tv_sale;
    TextView tv_order;
    TextView tv_generate_report;
    InventoryFragment fragment;
    SalesFragment salesFragment;
    FrameLayout main_container;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.copyDataBase(MainActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv_inventory=findViewById(R.id.tv_inventory);
        tv_sale=findViewById(R.id.tv_sale);
        tv_order=findViewById(R.id.tv_order);
        tv_generate_report=findViewById(R.id.tv_generate_report);
        dbHelper=new DbHelper(this);
        tv_inventory.setOnClickListener(this);
        tv_sale.setOnClickListener(this);
        tv_order.setOnClickListener(this);
        tv_generate_report.setOnClickListener(this);
        fragment=new InventoryFragment();
        salesFragment=new SalesFragment();
        main_container=findViewById(R.id.main_container);
        Utils.replaceFragment(MainActivity.this,fragment,"Inventory");


    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }else {
            finish();
        }
        System.exit(1);
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/



    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.tv_inventory){

            Utils.replaceFragment(MainActivity.this,fragment,"Inventory");
        }if(view.getId()==R.id.tv_sale){
            Utils.replaceFragment(MainActivity.this,salesFragment,"Sales");
        }if(view.getId()==R.id.tv_order){
            Utils.replaceFragment(MainActivity.this,new OrderFragment(),"Order");
        }
    }


    public void getKey(final View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                fragment.passDataToFragment(view);
            }
        }).start();
    }

    public void getKeySales(final View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                salesFragment.passDataToFragment(view);
            }
        }).start();
    }


}

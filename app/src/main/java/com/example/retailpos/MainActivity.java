package com.example.retailpos;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.retailpos.Helper.Utils;
import com.example.retailpos.fragment.Inventory.InventoryFragment;
import com.example.retailpos.fragment.Order.OrderFragment;
import com.example.retailpos.fragment.Sales.SalesFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_inventory;
    TextView tv_sale;
    TextView tv_order;
    TextView tv_generate_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv_inventory=findViewById(R.id.tv_inventory);
        tv_sale=findViewById(R.id.tv_sale);
        tv_order=findViewById(R.id.tv_order);
        tv_generate_report=findViewById(R.id.tv_generate_report);

        tv_inventory.setOnClickListener(this);
        tv_sale.setOnClickListener(this);
        tv_order.setOnClickListener(this);
        tv_generate_report.setOnClickListener(this);
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

            Utils.replaceFragment(MainActivity.this,new InventoryFragment());
        }if(view.getId()==R.id.tv_sale){
            Utils.replaceFragment(MainActivity.this,new SalesFragment());
        }if(view.getId()==R.id.tv_order){
            Utils.replaceFragment(MainActivity.this,new OrderFragment());
        }
    }

    
}

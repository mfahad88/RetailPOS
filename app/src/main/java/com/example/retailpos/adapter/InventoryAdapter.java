package com.example.retailpos.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.retailpos.R;
import com.example.retailpos.database.DbHelper;
import com.example.retailpos.dialog.BatchDialog;
import com.example.retailpos.interfaces.InterfaceInventory;
import com.example.retailpos.model.InventoryBean;
import com.example.retailpos.model.UI.InventoryUIBean;

import java.util.List;

public class InventoryAdapter extends ArrayAdapter {
    Context context;
    int resource;
    List<InventoryUIBean> objects;
    InterfaceInventory interfaceInventory;
    DbHelper dbHelper;
    public InventoryAdapter(@NonNull Context context, int resource, @NonNull List<InventoryUIBean> objects, InterfaceInventory interfaceInventory, DbHelper dbHelper) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
        this.interfaceInventory=interfaceInventory;
        this.dbHelper=dbHelper;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(resource,null);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        TextView tv_generic=convertView.findViewById(R.id.tv_generic);
//        TextView tv_potency=convertView.findViewById(R.id.tv_potency);
//        TextView tv_batch_no=convertView.findViewById(R.id.tv_batch_no);
//        TextView tv_exp_date=convertView.findViewById(R.id.tv_exp_date);
        TextView tv_qty=convertView.findViewById(R.id.tv_qty);
        TextView tv_cost=convertView.findViewById(R.id.tv_cost);
        TextView tv_price=convertView.findViewById(R.id.tv_price);
        ImageView img_delete=convertView.findViewById(R.id.img_delete);
//        ImageView img_edit=convertView.findViewById(R.id.img_edit);
        final InventoryUIBean bean=objects.get(position);

//        tv_batch_no.setText("");
        tv_cost.setText("");
//        tv_exp_date.setText("");
        tv_generic.setText("");
        tv_name.setText("");
//        tv_potency.setText("");
        tv_price.setText("");
        tv_qty.setText("");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            img_edit.setImageDrawable(context.getDrawable(R.drawable.ic_edit_black_24dp));
            img_delete.setImageDrawable(context.getDrawable(R.drawable.ic_delete_black_24dp));
        }

//        tv_batch_no.setText(bean.getBatch_no());
        tv_cost.setText(bean.getCost());
//        tv_exp_date.setText(bean.getExpiry_date());
        tv_generic.setText(bean.getGeneric());
        tv_name.setText(bean.getName());
        tv_price.setText(bean.getPrice());
        tv_qty.setText(bean.getQuantity());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BatchDialog dialogFragment=new BatchDialog();
                Bundle bundle=new Bundle();
                bundle.putInt("product_id",bean.getProduct_id());
                dialogFragment.setArguments(bundle);
                Activity activity=(Activity)context;
                dialogFragment.show(activity.getFragmentManager(),"dialog");
            }
        });
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, bean.toString(), Toast.LENGTH_SHORT).show();
                if(dbHelper.deleteProduct(bean.getProduct_id())>0 && dbHelper.deleteInvetory(bean.getProduct_id())>0 && dbHelper.deleteBatch(bean.getProduct_id())>0){
                    interfaceInventory.isInventory(true);
                }else{
                    interfaceInventory.isInventory(false);
                }

            }
        });
        return convertView;
    }
}

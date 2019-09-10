package com.example.retailpos.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.retailpos.R;
import com.example.retailpos.model.InventoryBean;

import java.util.List;

public class InventoryAdapter extends ArrayAdapter {
    Context context;
    int resource;
    List<InventoryBean> objects;
    public InventoryAdapter(@NonNull Context context, int resource, @NonNull List<InventoryBean> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(resource,null);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        TextView tv_generic=convertView.findViewById(R.id.tv_generic);
        TextView tv_potency=convertView.findViewById(R.id.tv_potency);
        TextView tv_batch_no=convertView.findViewById(R.id.tv_batch_no);
        TextView tv_exp_date=convertView.findViewById(R.id.tv_exp_date);
        TextView tv_qty=convertView.findViewById(R.id.tv_qty);
        TextView tv_cost=convertView.findViewById(R.id.tv_cost);
        TextView tv_price=convertView.findViewById(R.id.tv_price);
        ImageView img_delete=convertView.findViewById(R.id.img_delete);
        ImageView img_edit=convertView.findViewById(R.id.img_edit);
        InventoryBean bean=objects.get(position);

        tv_batch_no.setText("");
        tv_cost.setText("");
        tv_exp_date.setText("");
        tv_generic.setText("");
        tv_name.setText("");
        tv_potency.setText("");
        tv_price.setText("");
        tv_qty.setText("");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img_edit.setImageDrawable(context.getDrawable(R.drawable.ic_edit_black_24dp));
            img_delete.setImageDrawable(context.getDrawable(R.drawable.ic_delete_black_24dp));
        }

        return convertView;
    }
}

package com.example.retailpos.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.retailpos.R;
import com.example.retailpos.database.DbHelper;
import com.example.retailpos.interfaces.InterfaceBatch;
import com.example.retailpos.model.BatchBean;

import java.util.List;

public class BatchAdapter extends ArrayAdapter<BatchBean> {
    Context context;
    int resource;
    List<BatchBean> objects;
    DbHelper dbHelper;
    InterfaceBatch interfaceBatch;
    public BatchAdapter(@NonNull Context context, int resource, @NonNull List<BatchBean> objects, DbHelper dbHelper, InterfaceBatch interfaceBatch) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
        this.dbHelper=dbHelper;
        this.interfaceBatch=interfaceBatch;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(resource,null);
        TextView tv_batch_no=convertView.findViewById(R.id.tv_batch_no);
        TextView tv_exp_date=convertView.findViewById(R.id.tv_exp_date);
        TextView tv_qty=convertView.findViewById(R.id.tv_qty);
        ImageView img_delete=convertView.findViewById(R.id.img_delete);
        final BatchBean bean=objects.get(position);
        tv_batch_no.setText("");
        tv_exp_date.setText("");
        tv_qty.setText("");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img_delete.setImageDrawable(context.getDrawable(R.drawable.ic_delete_black_24dp));
        }
        tv_batch_no.setText(bean.getBatch_no());
        tv_exp_date.setText(bean.getExpiry_date());
        tv_qty.setText(String.valueOf(bean.getQuantity()));
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper.deleteBatch(bean.getBatch_no())>0) {
                    interfaceBatch.isBatch(true);
                }else{
                    interfaceBatch.isBatch(false);
                }
            }
        });
        return convertView;
    }
}

package com.example.retailpos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.retailpos.model.BatchBean;
import com.example.retailpos.model.InventoryBean;
import com.example.retailpos.model.MedicineBean;
import com.example.retailpos.model.ProductBean;
import com.example.retailpos.model.UI.InventoryUIBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sales_inventory_db.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    private Context context;
    SimpleDateFormat formatter;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /************************INSERT*********************/
    public long insertProduct(ProductBean bean){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("generic",bean.getGeneric());
        cv.put("name",bean.getName());
        cv.put("price_buying",bean.getPrice_buying());
        cv.put("price_selling",bean.getPrice_selling());
        cv.put("minimum",bean.getMinimum());
        cv.put("maximum",bean.getMaximum());
        cv.put("update_date",formatter.format(new Date()));
        Log.e("insertProduct--->","");
        long i=db.insert("Product",null,cv);
        db.close();
        return i;

    }

    public long insertBatch(BatchBean bean){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
//        cv.put("batch_id",bean.getBatch_id());
        cv.put("product_id",bean.getProduct_id());
        cv.put("batch_no",bean.getBatch_no());
        cv.put("expiry_date",bean.getExpiry_date());
        cv.put("quantity",bean.getQuantity());
        Log.e("insertBatch--->","");
        long i=db.insert("Batch",null,cv);
        db.close();
        return i;
    }

    public long insertInventory(InventoryBean bean){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("product_id",bean.getProduct_id());
        cv.put("quantity",bean.getQuantity());
        cv.put("update_date",formatter.format(new Date()));

        Log.e("insertInventory--->","");
        long i=db.insert("Inventory",null,cv);
        db.close();
        return i;
    }
    /*************************************************/

    /************************SELECT*********************/
    public List<ProductBean> getByNameProduct(ProductBean bean){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM Product WHERE name='"+bean.getName()+"'",null);
        List<ProductBean> list=new ArrayList<>();
        if(c.moveToFirst()){
            do{
                list.add(new ProductBean(c.getInt(c.getColumnIndex("product_id")),
                                        c.getString(c.getColumnIndex("generic")),
                                        c.getString(c.getColumnIndex("name")),
                                        c.getString(c.getColumnIndex("price_buying")),
                                        c.getString(c.getColumnIndex("price_selling")),
                                        c.getInt(c.getColumnIndex("minimum")),
                                        c.getInt(c.getColumnIndex("maximum"))));
            }while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public List<BatchBean> getByIdBatch(int product_id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Batch WHERE product_id="+product_id,null);
        List<BatchBean> list=new ArrayList<>();
        if(c.moveToFirst()){
            do{
                list.add(new BatchBean(
                        c.getInt(c.getColumnIndex("batch_id")),
                        c.getInt(c.getColumnIndex("product_id")),
                        c.getString(c.getColumnIndex("batch_no")),
                        c.getString(c.getColumnIndex("expiry_date")),
                        c.getInt(c.getColumnIndex("quantity"))));
            }while (c.moveToNext());
        }
        return list;
    }

    public List<InventoryUIBean> getAllProductInventory(){
        SQLiteDatabase db=this.getReadableDatabase();
//        Cursor c=db.rawQuery("SELECT P.product_id,P.generic,P.name,P.price_buying,P.price_selling,P.minimum,P.maximum,B.batch_id,B.batch_no,B.expiry_date,B.quantity FROM Product AS P JOIN Batch AS B WHERE P.product_id=B.product_id",null);
        Cursor c=db.rawQuery("SELECT * FROM Product AS P JOIN Inventory AS I ON P.product_id=I.product_id",null);
        List<InventoryUIBean> list=new ArrayList<>();
        if(c.moveToFirst()){
            do{
                list.add(new InventoryUIBean(c.getInt(c.getColumnIndex("product_id")),
//                        c.getInt(c.getColumnIndex("batch_id")),
                        c.getString(c.getColumnIndex("name")),
                        c.getString(c.getColumnIndex("generic")),
//                        c.getString(c.getColumnIndex("batch_no")),
//                        c.getString(c.getColumnIndex("expiry_date")),
                        String.valueOf(c.getInt(c.getColumnIndex("quantity"))),
                        c.getString(c.getColumnIndex("price_buying")),
                        c.getString(c.getColumnIndex("price_selling"))));
                Log.e("List---->",list.toString());
            }while (c.moveToNext());
        }
        c.close();
        db.close();

        return list;
    }

    public List<BatchBean> getByProductIdBatch(BatchBean bean){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM Batch WHERE product_id="+bean.getProduct_id()+" AND batch_no="+bean.getBatch_no(),null);
        List<BatchBean> list=new ArrayList<>();
        if(c.moveToFirst()){
            do{
                list.add(new BatchBean(c.getInt(c.getColumnIndex("batch_id")),
                        c.getInt(c.getColumnIndex("product_id")),
                        c.getString(c.getColumnIndex("batch_no")),
                        c.getString(c.getColumnIndex("expiry_date")),
                        c.getInt(c.getColumnIndex("quantity"))));

            }while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public List<InventoryBean> getAllInventory(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM Inventory",null);
        List<InventoryBean> list=new ArrayList<>();
        if(c.moveToFirst()){
            do{
                list.add(new InventoryBean(c.getInt(c.getColumnIndex("inventory_id")),
                        c.getInt(c.getColumnIndex("product_id")),
                        c.getInt(c.getColumnIndex("quantity"))));
            }while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public List<InventoryBean> getByIdInventory(InventoryBean bean){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM Inventory WHERE product_id="+bean.getProduct_id(),null);
        List<InventoryBean> list=new ArrayList<>();
        if(c.moveToFirst()){
            do{
                list.add(new InventoryBean(c.getInt(c.getColumnIndex("inventory_id")),
                        c.getInt(c.getColumnIndex("product_id")),
                        c.getInt(c.getColumnIndex("quantity"))));
            }while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public List<MedicineBean> findByNameMedicine(String name){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT P.name || \" ( \" || B.expiry_date || \" )\" AS name , P.product_id,B.batch_id,P.price_buying,P.price_selling,B.quantity FROM Product AS P inner join Batch AS B ON P.product_id=B.product_id WHERE P.name like '"+name+"%'",null);
        List<MedicineBean> list=new ArrayList<>();
        if(c.moveToFirst()){
            do{
                list.add(new MedicineBean(c.getInt(c.getColumnIndex("product_id")),
                        c.getInt(c.getColumnIndex("batch_id")),
                        c.getString(c.getColumnIndex("name")),
                        c.getInt(c.getColumnIndex("quantity")),
                        c.getString(c.getColumnIndex("price_buying")),
                        c.getString(c.getColumnIndex("price_selling"))
                ));
            }while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }
    /*************************************************/

    /************************UPDATE*********************/
    public int updateProduct(ProductBean bean){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("generic",bean.getGeneric());
//        cv.put("name",bean.getName());
        cv.put("price_buying",bean.getPrice_buying());
        cv.put("price_selling",bean.getPrice_selling());
        cv.put("minimum",bean.getMinimum());
        cv.put("maximum",bean.getMaximum());
        cv.put("modified_date",formatter.format(new Date()));
        Log.e("UpdateProduct--->","");
        return db.update("Product",cv,"name=?",new String[]{bean.getName()});
    }

    public int updateBatch(BatchBean bean){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
//        cv.put("batch_id",bean.getBatch_id());
        cv.put("product_id",bean.getProduct_id());
//        cv.put("batch_no",bean.getBatch_no());
        cv.put("expiry_date",bean.getExpiry_date());
        cv.put("quantity",bean.getQuantity());
        Log.e("UpdateBatch--->","");
        return db.update("Batch",cv,"batch_no=?",new String[]{bean.getBatch_no()});
    }

    public int updateInventory(InventoryBean bean){
        SQLiteDatabase db=this.getWritableDatabase();
        SQLiteDatabase db_select=this.getReadableDatabase();
        int product_id=-1;
        int quantity=-1;
        Cursor c=db_select.rawQuery("SELECT * FROM Inventory WHERE product_id="+bean.getProduct_id(),null);
        if(c.moveToFirst()){
            do{
                product_id=c.getInt(c.getColumnIndex("product_id"));
                quantity=c.getInt(c.getColumnIndex("quantity"));
            }while (c.moveToNext());
        }
        ContentValues cv=new ContentValues();
        cv.put("product_id",product_id);
        cv.put("quantity",quantity+bean.getQuantity());
        cv.put("modified_date",formatter.format(new Date()));
        return db.update("Inventory",cv,"product_id=?",new String[]{String.valueOf(bean.getProduct_id())});


//        return db.update("Inventory",cv,"product_id=?",new String[]{String.valueOf(bean.getProduct_id())});
    }



    /*************************************************/

    /***************************DELETE**********************/
    public int deleteProduct(int product_id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Product","product_id=?",new String[]{String.valueOf(product_id)});
    }

    public int deleteInvetory(int product_id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Inventory","product_id=?",new String[]{String.valueOf(product_id)});
    }

    public int deleteBatch(String batch_no){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Batch","batch_no=?",new String[]{String.valueOf(batch_no)});
    }

    public int deleteBatch(int product_id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Batch","product_id=?",new String[]{String.valueOf(product_id)});
    }
}

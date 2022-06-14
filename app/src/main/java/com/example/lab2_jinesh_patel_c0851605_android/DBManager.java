package com.example.lab2_jinesh_patel_c0851605_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION= 1;
    private static final String  DATABASE_NAME="LabTest2_Jinesh_product";
    private static final String TABLE_NAME="products";
    private static final String ID="id";
    private static final String pID ="pid";
    private static final String pName ="pname";
    private static final String pPrice ="pprice";
    private static final String pDescription ="pdecription";
    private static final String createdAt ="created_at";


    public DBManager(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_query="CREATE TABLE if not EXISTS "+TABLE_NAME+
                "("+
                ID+" INTEGER PRIMARY KEY,"+
                pID +" TEXT ,"+
                pName +" TEXT ,"+
                pPrice + " TEXT ,"+
                pDescription +" TEXT ,"+
                createdAt + " TEXT "+
                ")";
        db.execSQL(table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public void deleteProduct(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,ID+"=?",new String[]{id});
        db.close();
    }

    public int updateProduct(Product productModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(pID, productModel.getproduct_id());
        contentValues.put(pName, productModel.getProduct_name());
        contentValues.put(pDescription, productModel.getProduct_description());
        contentValues.put(pPrice, productModel.getProduct_price());
        return db.update(TABLE_NAME,contentValues,ID+"=?",new String[]{String.valueOf(productModel.getId())});
    }

    public Product getProduct(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new String[]{ID, pID, pName, pDescription, pPrice, createdAt},ID+" = ?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Product productModel =new Product(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        db.close();
        return productModel;
    }

    public List<Product> getAllProducts(){
        List<Product> productModelList =new ArrayList<>();
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Product productModel =new Product(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getString(3),cursor.getString(5));
                productModelList.add(productModel);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return productModelList;
    }

    public void AddProduct(Product productModel){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(pID, productModel.getproduct_id());
        contentValues.put(pName, productModel.getProduct_name());
        contentValues.put(pDescription, productModel.getProduct_description());
        contentValues.put(pPrice, productModel.getProduct_price());
        contentValues.put(createdAt, productModel.getCreated_at());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public int getTotalCount(){
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor.getCount();
    }
}
package com.examples.rehan.excluzo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.examples.rehan.excluzo.Models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rehan r on 04-10-2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productmanager";
    private static final String TABLE_PRODUCTS= "product";

    // product Table Columns names
    private static final String KEY_ID = "produt_id";
    private static final String KEY_NAME = "product_name";
    private static final String KEY_PRICE = "product_price";
    private static final String KEY_RATINGS = "product_rating";
    private static final String KEY_IMAGE = "product_image";

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PRICE + " TEXT," + KEY_RATINGS + " TEXT," + KEY_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, product.getProductid());
        values.put(KEY_NAME, product.getProductname());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_RATINGS,String.valueOf(product.getRatings()));
        values.put(KEY_IMAGE,product.getImage_url());
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }


    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<Product>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setProductid(cursor.getString(0));
                product.setProductname(cursor.getString(1));
                product.setPrice(Integer.parseInt(cursor.getString(2)));
                product.setRatings(Double.parseDouble(cursor.getString(3)));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        return productList;
    }


    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_ID + " = ?",
                new String[] { String.valueOf(product.getProductid()) });
        db.close();
    }

    public int getProductCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}


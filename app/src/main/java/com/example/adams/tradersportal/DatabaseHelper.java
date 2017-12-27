package com.example.adams.tradersportal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Adams on 23-12-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mylist.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase database;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateQuery_.CREATE_TABLE_SELL_PRODUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants_.SELL_PRODUCT);
        onCreate(db);
    }

    // open database in write mode to make fetch/insert query
    private void openDatabase() {
        database = this.getReadableDatabase();
    }

    //close database once operations is done
    private void closeDatabase() {
        if (database != null && database.isOpen())
            database.close();
    }

    boolean insertSellProduct(String name, String price) {
        ContentValues sellProductValues = new ContentValues();
        sellProductValues.put(Constants_.NAME, name);
        sellProductValues.put(Constants_.PRICE, price);
        sellProductValues.put(Constants_.TIMESTAMP, DateTime_.getCurrentTimestamp());

        openDatabase();
        long id = database.insert(Constants_.SELL_PRODUCT, null, sellProductValues);
        closeDatabase();
        return id > 0;
    }

    ArrayList<SellProduct_> fetchSellProducts() {

        ArrayList<SellProduct_> sellProductArrayList = new ArrayList<>();

        openDatabase();

        String sql = "SELECT * FROM " + Constants_.SELL_PRODUCT;

        Cursor mCursor = database.rawQuery(sql, null);

        if (mCursor != null) {
            if (mCursor.moveToFirst()) {
                while (!mCursor.isAfterLast()) {
                    SellProduct_ sellProduct_ = new SellProduct_();

                    sellProduct_.setId(mCursor.getInt(mCursor.getColumnIndex(Constants_.ID)));
                    sellProduct_.setName(mCursor.getString(mCursor.getColumnIndex(Constants_.NAME)));
                    sellProduct_.setPrice(mCursor.getString(mCursor.getColumnIndex(Constants_.PRICE)));
                    sellProduct_.setTimestamp(mCursor.getInt(mCursor.getColumnIndex(Constants_.TIMESTAMP)));

                    sellProductArrayList.add(sellProduct_);
                    mCursor.moveToNext();
                }
            }
            mCursor.close();
        }
        Collections.reverse(sellProductArrayList);
        return sellProductArrayList;
    }
  /*  boolean addData(String item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }*/
}

package com.cse5324.projecthotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test";
    public static final String TABLE_NAME = "testtable";
    public static final String COL_1 = "USERNAME";
    public static final String COL_2 = "PASSWORD";
    public static final String COL_3 = "FIRSTNAME";
    public static final String COL_4 = "LASTNAME";
    public static final String COL_5 = "PHONE";
    public static final String COL_6 = "EMAIL";
    public static final String COL_7 = "ADDRESS";
    public static final String COL_8 = "CITY";
    public static final String COL_9 = "STATE";
    public static final String COL_10 = "ZIPCODE";
    public static final String COL_11 = "CREDITCARDNO";
    public static final String COL_12 = "CREDITCARDEXPIRY";
    public static final String COL_13 = "ROLE";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD INTEGER,FIRSTNAME TEXT,LASTNAME TEXT,PHONE INTEGER,EMAIL INTEGER,ADDRESS INTEGER, CITY TEXT,STATE TEXT,ZIPCODE INTEGER,CREDITCARDNO INTEGER,CREDITCARDEXPIRY INTEGER,ROLE TEXT)");
    }

    public boolean insertData(String username,String password,String firstname,String lastname,String phone,String email,String address,String city,
                              String state,String zipcode,String creditcardno,String creditcardexpiry, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,username);
        contentValues.put(COL_2,password);
        contentValues.put(COL_3,firstname);
        contentValues.put(COL_4,lastname);
        contentValues.put(COL_5,phone);
        contentValues.put(COL_6,email);
        contentValues.put(COL_7,address);
        contentValues.put(COL_8,city);
        contentValues.put(COL_9,state);
        contentValues.put(COL_10,zipcode);
        contentValues.put(COL_11,creditcardno);
        contentValues.put(COL_12,creditcardexpiry);
        contentValues.put(COL_13,role);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

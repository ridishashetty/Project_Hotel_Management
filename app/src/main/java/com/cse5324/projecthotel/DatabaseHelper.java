package com.cse5324.projecthotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test.db";
    public static final String TABLE_NAME = "testtable";
    public static final String TABLE_NAME1 = "user_reservations";
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
    SQLiteDatabase db;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD INTEGER, FIRSTNAME TEXT,LASTNAME TEXT,PHONE INTEGER,EMAIL INTEGER, ADDRESS INTEGER, CITY TEXT,STATE TEXT,ZIPCODE INTEGER,CREDITCARDNO INTEGER,CREDITCARDEXPIRY DATE,ROLE TEXT)");
    }

    public boolean insertData(ContentValues ip) {
        //// new values registration guest
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, ip.getAsString(COL_1));
        cv.put(COL_2, ip.getAsString(COL_2));
        cv.put(COL_3, ip.getAsString(COL_3));
        cv.put(COL_4, ip.getAsString(COL_4));
        cv.put(COL_5, ip.getAsString(COL_5));
        cv.put(COL_6, ip.getAsString(COL_6));
        cv.put(COL_7, ip.getAsString(COL_7));
        cv.put(COL_8, ip.getAsString(COL_8));
        cv.put(COL_9, ip.getAsString(COL_9));
        cv.put(COL_10, ip.getAsString(COL_10));
        cv.put(COL_11, ip.getAsString(COL_11));
        cv.put(COL_12, ip.getAsString(COL_12));
        cv.put(COL_13, ip.getAsString(COL_13));

        long result = db.insert(TABLE_NAME,null ,cv);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor ValidateUser(String username, String password) {
        db = this.getReadableDatabase();
        String queryForCheckingPassword = "Select * from "+TABLE_NAME+" where USERNAME = '" + username + "' and PASSWORD = '"+password+"'";
        Cursor cursor = db.rawQuery(queryForCheckingPassword, null);

        ////////////////
        return cursor;
    }
    public Cursor ViewData(String hotel, String date)
    {
        db = this.getReadableDatabase();
        String viewlistquery = "SELECT * FROM "+TABLE_NAME1+" WHERE HOTELNAME = '" + hotel + "' AND CHECKINDATE >= '" + date + "'";
        Cursor cursor = db.rawQuery(viewlistquery, null);
        return  cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public Cursor getUsers()
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_NAME, null);

        return cursor;
    }
    public void deleteFrom() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}

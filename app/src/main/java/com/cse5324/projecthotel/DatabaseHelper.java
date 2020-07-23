package com.cse5324.projecthotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    SQLiteDatabase db;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        ///////////////////////////////////////////////////////////////////
        //admin
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues admin = new ContentValues();
        admin.put(COL_1, "Tony");
        admin.put(COL_2, "admin");
        admin.put(COL_3, "Tony");
        admin.put(COL_4, "Dsouza");
        admin.put(COL_5, "3213243242");
        admin.put(COL_6, "admin@hotels.com");
        admin.put(COL_7, "lives at, 120 palace, 20th street");
        admin.put(COL_8, "nowhere");
        admin.put(COL_9, "nothere");
        admin.put(COL_10, "10010");
        admin.put(COL_11, "1234123412341234");
        admin.put(COL_12, "08/19/24");
        admin.put(COL_13, "a");
        if(db.insert(TABLE_NAME,null , admin) == -1)
        {}

        ///5 managers
        ContentValues m1 = new ContentValues();
        m1.put(COL_1, "Joe");
        m1.put(COL_2, "joe123");
        m1.put(COL_3, "Joe");
        m1.put(COL_4, "Dsouza");
        m1.put(COL_5, "8913243242");
        m1.put(COL_6, "man1@hotels.com");
        m1.put(COL_7, "lives at, manhotel, manhattan");
        m1.put(COL_8, "somewhere");
        m1.put(COL_9, "wherever");
        m1.put(COL_10, "23010");
        m1.put(COL_11, "890123412777234");
        m1.put(COL_12, "07/20/22");
        m1.put(COL_13, "m");
        if(db.insert(TABLE_NAME,null ,m1) == -1)
        {}
        ContentValues m2 = new ContentValues();
        m2.put(COL_1, "Jacob");
        m2.put(COL_2, "jacob123");
        m2.put(COL_3, "Jacob");
        m2.put(COL_4, "Sirius");
        m2.put(COL_5, "7364577782");
        m2.put(COL_6, "man2@hotels.com");
        m2.put(COL_7, "lives at, broom street, newland");
        m2.put(COL_8, "athere");
        m2.put(COL_9, "there");
        m2.put(COL_10, "23780");
        m2.put(COL_11, "9090341279723884");
        m2.put(COL_12, "07/20/22");
        m2.put(COL_13, "m");
        if(db.insert(TABLE_NAME,null ,m2) == -1)
        {}
        ContentValues m3 = new ContentValues();
        m3.put(COL_1, "Kyle");
        m3.put(COL_2, "kyle123");
        m3.put(COL_3, "Kyle");
        m3.put(COL_4, "Sain");
        m3.put(COL_5, "9364909082");
        m3.put(COL_6, "man3@hotels.com");
        m3.put(COL_7, "lives at, frog street, waters");
        m3.put(COL_8, "leafland");
        m3.put(COL_9, "greens");
        m3.put(COL_10, "90909");
        m3.put(COL_11, "4440341279700004");
        m3.put(COL_12, "02/10/24");
        m3.put(COL_13, "m");
        if(db.insert(TABLE_NAME,null ,m3) == -1)
        {}
        ContentValues m4 = new ContentValues();
        m4.put(COL_1, "Drake");
        m4.put(COL_2, "drake123");
        m4.put(COL_3, "Drake");
        m4.put(COL_4, "Code");
        m4.put(COL_5, "3233309082");
        m4.put(COL_6, "man4@hotels.com");
        m4.put(COL_7, "lives at, three street, thirteenth");
        m4.put(COL_8, "tree");
        m4.put(COL_9, "hunders");
        m4.put(COL_10, "99459");
        m4.put(COL_11, "4490321579743767");
        m4.put(COL_12, "08/01/24");
        m4.put(COL_13, "m");
        if(db.insert(TABLE_NAME,null ,m4) == -1)
        {}
        ContentValues m5 = new ContentValues();
        m5.put(COL_1, "Evan");
        m5.put(COL_2, "evan123");
        m5.put(COL_3, "Evan");
        m5.put(COL_4, "Brooks");
        m5.put(COL_5, "9444905435");
        m5.put(COL_6, "man5@hotels.com");
        m5.put(COL_7, "lives at, distill, drink");
        m5.put(COL_8, "canned");
        m5.put(COL_9, "opener");
        m5.put(COL_10, "86757");
        m5.put(COL_11, "7567641289700364");
        m5.put(COL_12, "02/13/24");
        m5.put(COL_13, "m");
        if(db.insert(TABLE_NAME,null ,m5) == -1)
        {}
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD INTEGER, FIRSTNAME TEXT,LASTNAME TEXT,PHONE INTEGER,EMAIL INTEGER, ADDRESS INTEGER, CITY TEXT,STATE TEXT,ZIPCODE INTEGER,CREDITCARDNO INTEGER,CREDITCARDEXPIRY DATE,ROLE TEXT)");
    }

    public boolean insertData(String username,String password,String firstname,String lastname,String phone,String email,String address,String city,
                              String state,String zipcode,String creditcardno,String creditcardexpiry, String role) {
        //// new values registration guest
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1,username);
        cv.put(COL_2,password);
        cv.put(COL_3,firstname);
        cv.put(COL_4,lastname);
        cv.put(COL_5,phone);
        cv.put(COL_6,email);
        cv.put(COL_7,address);
        cv.put(COL_8,city);
        cv.put(COL_9,state);
        cv.put(COL_10,zipcode);
        cv.put(COL_11,creditcardno);
        cv.put(COL_12,creditcardexpiry);
        cv.put(COL_13,role);

        long result = db.insert(TABLE_NAME,null ,cv);
        if(result == -1)
            return false;
        else
            return true;
    }

    public String ValidateUser(String username, String password) {
        db = this.getReadableDatabase();
        String queryForCheckingPassword = "Select * from "+TABLE_NAME+" where USERNAME = '" + username + "' and PASSWORD = '"+password+"'";
        Cursor cursor = db.rawQuery(queryForCheckingPassword, null);
        ////////////////
        String res="";
        if (cursor.moveToFirst()) {         //cursor.moveToFirst()
            res = cursor.getString(cursor.getColumnIndex(TABLE_NAME.concat(".ROLE")));
        }
        else
        {
            res = "error";
        }
        ////////////////
        return res;      //return cursor.getCount() > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

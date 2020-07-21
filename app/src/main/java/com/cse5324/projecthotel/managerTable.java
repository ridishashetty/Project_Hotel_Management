package com.cse5324.projecthotel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class managerTable extends SQLiteOpenHelper{
    public managerTable(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("ID INTEGER primary key autoincrement, name VARCHAR(20), hotel_id INTEGER, " +
          //      "CONSTRAINT f FOREIGN KEY (hotel_id) REFERENCES hotel(ID), Username VARCHAR(20)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

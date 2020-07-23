package com.cse5324.projecthotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class reservationsTable extends SQLiteOpenHelper{
    SQLiteDatabase db;

    //SQLiteDatabase db;
    public reservationsTable(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public reservationsTable(Context context) {
        super(context, "test", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE reservations(ID integer primary key, user_id integer, status VARCHAR(25), fromDate DATE, toDate DATE, room_id INTEGER, CONSTRAINT u FOREIGN KEY (user_id) REFERENCES testtable(ID), CONSTRAINT r FOREIGN KEY (room_id) REFERENCES roomTable(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

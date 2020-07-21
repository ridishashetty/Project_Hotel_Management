package com.cse5324.projecthotel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class hotelTable extends SQLiteOpenHelper {
    public hotelTable(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE hotel (ID integer primary key autoincrement, manager_id INTEGER, name VARCHAR(20), " +
                "location VARCHAR(50), CONSTRAINT a FOREIGN KEY(manager_id) REFERENCES test(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

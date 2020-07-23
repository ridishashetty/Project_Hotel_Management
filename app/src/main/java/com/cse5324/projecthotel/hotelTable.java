package com.cse5324.projecthotel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class hotelTable extends SQLiteOpenHelper {
    public static final String COL_1 = "manager_id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "location";
    SQLiteDatabase db;

    public hotelTable(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public hotelTable(Context context) {
        super(context, "test", null, 1);
        //Default hotel names permanent: Maverick, Ranger, Williams, Shard, and Liberty
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues h1 = new ContentValues();
        h1.put(COL_1, "1");
        h1.put(COL_2, "Maverick");
        h1.put(COL_3, "Arlington");
        if(db.insert("hotel",null , h1) == -1)
        {}
        ContentValues h2 = new ContentValues();
        h2.put(COL_1, "2");
        h2.put(COL_2, "Ranger");
        h2.put(COL_3, "Arlington");
        if(db.insert("hotel",null , h2) == -1)
        {}
        ContentValues h3 = new ContentValues();
        h3.put(COL_1, "3");
        h3.put(COL_2, "Williams");
        h3.put(COL_3, "Arlington");
        if(db.insert("hotel",null , h3) == -1)
        {}
        ContentValues h4 = new ContentValues();
        h4.put(COL_1, "4");
        h4.put(COL_2, "Shard");
        h4.put(COL_3, "Arlington");
        if(db.insert("hotel",null , h4) == -1)
        {}
        ContentValues h5 = new ContentValues();
        h5.put(COL_1, "5");
        h5.put(COL_2, "Liberty");
        h5.put(COL_3, "Arlington");
        if(db.insert("hotel",null , h5) == -1)
        {}
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("CREATE TABLE hotel (ID integer primary key autoincrement, manager_id INTEGER, name VARCHAR(20), " +
                "location VARCHAR(50), CONSTRAINT a FOREIGN KEY(manager_id) REFERENCES testtable(ID))");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.cse5324.projecthotel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class roomTable extends SQLiteOpenHelper {
    public static final String COL_1 = "hotel_id";
    public static final String COL_2 = "room_no";
    public static final String COL_3 = "room_type";
    SQLiteDatabase db;

    public roomTable(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public roomTable(Context context) {
        super(context, "test", null, 1);
        ///Default rooms: no. of rows don't change only values do
        SQLiteDatabase db = this.getWritableDatabase();
        int k=1;
        for(int j=1; j<=5; j++)  //5 hotels
        {
            while(k<=4)  //4 floors
            {
                for(int l=1;l<=25; l++)  //25 rooms per floor
                {
                    ContentValues r = new ContentValues();
                    r.put(COL_1, j);
                    if(l<10)
                    {
                        r.put(COL_2, k+"0"+l);
                    }
                    else
                    {
                        r.put(COL_2, k+l);
                    }
                    if(k<4)
                    {
                        r.put(COL_3, "Standard");
                    }
                    else
                    {
                        if(l<=13)
                        {
                            r.put(COL_3, "Standard");
                        }
                        else
                        {
                            if(l<=21)
                            {
                                r.put(COL_3, "Deluxe");
                            }
                            else
                            {
                                r.put(COL_3, "Suite");
                            }
                        }
                    }
                    if (db.insert("room", null, r) == -1) {
                    }
                }
                k++;
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     /*   db.execSQL("CREATE TABLE room (ID INTEGER PRIMARY KEY, hotel_id INTEGER, room_no INTEGER, " +
                "roomType VARCHAR(25), guest_id INTEGER, checkin DATE, checkout DATE, status VARCHAR(25)," +
                "CONSTRAINT h FOREIGN KEY(hotel_id) REFERENCES hotel(ID), " +
                "CONSTRAINT g FOREIGN KEY(guest_id) REFERENCES testable(ID))"); */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

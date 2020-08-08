package com.cse5324.projecthotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class hotelDatabase extends SQLiteOpenHelper {
    ///Initialize variables
    private static final String DATABASE_NAME = "hotel_DB";
    private static final String TABLE_RESERVATION = "reservation";
    private static final String TABLE_ROOM = "room";
    private static final String TABLE_HOTEL = "hotel";

    hotelDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create tables
        String tbl_room = "CREATE TABLE "+TABLE_ROOM+"(ID INTEGER PRIMARY KEY, hotel_id INTEGER, room_no INTEGER, " +
                "roomType VARCHAR(25), guest_id INTEGER, checkin DATE, checkout DATE, status VARCHAR(25), " +
                "CONSTRAINT h FOREIGN KEY(hotel_id) REFERENCES hotel(ID), CONSTRAINT g FOREIGN KEY(guest_id) REFERENCES testable(ID))";
        String tbl_hotel = "CREATE TABLE "+TABLE_HOTEL+"(ID integer primary key autoincrement, manager_id INTEGER, " +
                "name VARCHAR(20), location VARCHAR(50), CONSTRAINT a FOREIGN KEY(manager_id) REFERENCES testtable(ID))";
        String tbl_res = "CREATE TABLE "+TABLE_RESERVATION+"(ID integer primary key, user_id integer," +
                " fromDate DATE, toDate DATE, hotel_id integer, room_num integer, status VARCHAR(20), CONSTRAINT u FOREIGN KEY (user_id) REFERENCES testtable(ID)," +
                " CONSTRAINT r FOREIGN KEY (room_num) REFERENCES roomTable(room_no))";
        String tbl_prices = "CREATE TABLE price(ID integer primary key autoincrement, roomType VARCHAR(20), dayType VARCHAR(20), costPerNight INTEGER)";
        //create tables
        db.execSQL(tbl_prices);
        db.execSQL(tbl_room);
        db.execSQL(tbl_hotel);
        db.execSQL(tbl_res);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_HOTEL);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_RESERVATION);
        onCreate(db);
    }

    //insert to table 'room'
    public boolean insertRoom (ContentValues ip)
    {
        SQLiteDatabase sqldb = this.getWritableDatabase();

        //Create content values
        ContentValues cv1 = new ContentValues();
        cv1.put("hotel_id", ip.getAsString("hotel_id"));
        cv1.put("room_no", ip.getAsString("room_no"));
        cv1.put("roomType", ip.getAsString("roomType"));
        cv1.putNull("guest_id");
        cv1.putNull("checkin");
        cv1.putNull("checkout");
        cv1.put("status", ip.getAsString("status"));
        long res = sqldb.insert(TABLE_ROOM, null, cv1);
        if(res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //insert to table 'hotel'
    public boolean insertHotel (ContentValues cv)
    {
        SQLiteDatabase sqldb = this.getWritableDatabase();

        //Create content values
        ContentValues cv2 = new ContentValues();
        cv2.put("manager_id", cv.getAsString("manager"));
        cv2.put("name", cv.getAsString("hotel"));
        cv2.put("location", cv.getAsString("location"));

        long res = sqldb.insert(TABLE_HOTEL, null, cv2);
        if(res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //insert to table 'reservation'
    public boolean insertReservation (ContentValues ip)
    {
        SQLiteDatabase sqldb = this.getWritableDatabase();

        //Create content values
        ContentValues cv3 = new ContentValues();
        cv3.put("user_id", ip.getAsString("user_id"));
        cv3.put("fromDate", ip.getAsString("fromDate"));
        cv3.put("toDate", ip.getAsString("toDate"));
        cv3.put("room_id", ip.getAsString("room_id"));
        long res = sqldb.insert(TABLE_RESERVATION, null, cv3);
        if(res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean insertPrice(ContentValues cv)
    {
        SQLiteDatabase sqldb = this.getWritableDatabase();

        //Create content values
        ContentValues cv2 = new ContentValues();
        cv2.put("roomType", cv.getAsString("roomType"));
        cv2.put("dayType", cv.getAsString("dayType"));
        cv2.put("costPerNight", cv.getAsString("cpn"));

        long res = sqldb.insert("price", null, cv2);
        if(res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //get values from table "room"
    public Cursor getRoom()
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_ROOM+" WHERE status='available'", null);

        return cursor;
    }
    public int getRoomCount(String roomT, int hotelID)
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_ROOM+" WHERE status='available' AND roomType='"+roomT+"' AND hotel_id='"+hotelID+"'", null);

        return cursor.getCount();
    }

    public Cursor getRoomById(String id)
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_ROOM+" WHERE status='available' AND id='"+id+"'", null);

        return cursor;
    }

    //get values from table "hotel"
    public Cursor getHotel()
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_HOTEL, null);

        return cursor;
    }
    public Cursor getPrice(String roomType, String dayType)
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor=sqldb.rawQuery("SELECT * FROM price", null);;
        if(!roomType.equals("")&&!dayType.equals(""))
        {
            cursor = sqldb.rawQuery("SELECT * FROM price WHERE roomType='"+roomType+"' AND dayType='"+dayType+"'", null);
        }

        return cursor;
    }

    //get values from table "reservation"
    public Cursor getReservation()
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_RESERVATION, null);

        return cursor;
    }

    public void deleteFrom() {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DELETE FROM "+TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_HOTEL);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_RESERVATION);
        db.execSQL("DROP TABLE IF EXISTS price");
        onCreate(db);
    }

    public boolean updateRoomStatus(String stat, String id)
    {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        Cursor cursor = sqldb.rawQuery("UPDATE "+TABLE_ROOM+" SET status='"+stat+"' WHERE id='"+id+"'", null);
        if(cursor.getCount()==0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}

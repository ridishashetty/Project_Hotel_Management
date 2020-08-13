package com.cse5324.projecthotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
                "roomType VARCHAR(25), guest_id INTEGER, checkin DATE, checkout DATE, status VARCHAR(25), reservation_id INTEGER)";
        String tbl_hotel = "CREATE TABLE "+TABLE_HOTEL+"(ID integer primary key autoincrement, manager_id INTEGER, " +
                "name VARCHAR(20), location VARCHAR(50))";
        String tbl_res = "CREATE TABLE "+TABLE_RESERVATION+"(ID integer primary key, user_id integer, numOfRooms integer, hotel_id integer, " +
                " rType VARCHAR(20), fromDate DATE, toDate DATE, inTime VARCHAR(20), amountPaid integer, status VARCHAR(20), cardPin integer)";
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
        cv3.put("numOfRooms", ip.getAsString("numRooms"));
        cv3.put("inTime", ip.getAsString("inTime"));
        cv3.put("hotel_id", ip.getAsString("hotel_id"));
        cv3.put("status", ip.getAsString("status"));
        cv3.put("rType", ip.getAsString("rType"));
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

    public boolean updateReservation(int id, int amt, int pin, String btnClicked)
    {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        //String c="DELETE FROM reservation WHERE ID='" + id + "'";
        boolean ret=false;
        if(btnClicked.toLowerCase().contains("confirm")) {
            Log.i("event: ", "confirm");
            ContentValues cv=new ContentValues();
            cv.put("amountPaid", amt);
            cv.put("cardPin", pin);
            cv.put("status", "confirmed");
            int res=sqldb.update(TABLE_RESERVATION, cv, "ID="+id, null);
            if(res==0)
            {
                ret=false;
            }
            else
            {
                ret=true;
            }
        }
        else if(btnClicked.toLowerCase().contains("cancel")){
            Log.i("event: ", "cancel");
            int res=sqldb.delete(TABLE_RESERVATION, "ID="+id, null);
            if(res==0)
            {
                ret=false;
            }
            else
            {
                ret=true;
            }
        }
        else{
            Log.i("event: ", "its over here");
        }
        return ret;
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
    public Cursor getRoom(String roomType, String hotel_id)
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_ROOM+" WHERE status='available'", null);
        if(!roomType.equals("") && !hotel_id.equals(""))
        {
            cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_ROOM+" WHERE status='available' AND hotel_id='"+hotel_id+"' AND roomType='"+roomType+"'", null);
        }

        return cursor;
    }
    public int getRoomCount(String roomT, int hotelID)
    {
        //Log.i("dbVal: ", roomT+" "+hotelID);
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_ROOM+" WHERE status='available' AND roomType='"+roomT+"' AND hotel_id='"+hotelID+"'", null);
        cursor.moveToFirst();

//        Log.i("dbVal: ", Integer.toString(cursor.getCount()));
        return cursor.getCount();
    }

    public Cursor getRoomById(int id)
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_ROOM+" WHERE id='"+id+"'", null);

        return cursor;
    }

    //get values from table "hotel"
    public Cursor getHotel(String hotel)
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        Cursor cursor = sqldb.rawQuery("SELECT * FROM " + TABLE_HOTEL, null);
        ///Perform RawQuery
        if(!hotel.equals("")) {
            String regex = "\\d+";
            if(hotel.matches(regex))    //id passed
            {
                cursor = sqldb.rawQuery("SELECT * FROM " + TABLE_HOTEL + " WHERE id='"+hotel+"'", null);
            }
            else    //name passed
            {
                cursor = sqldb.rawQuery("SELECT * FROM " + TABLE_HOTEL + " WHERE name='"+hotel+"'", null);
            }
        }

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
    public Cursor getReservation(int user)
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_RESERVATION+" WHERE user_id='"+user+"' ORDER BY fromDate", null);

        return cursor;
    }

    public Cursor getConfirmedReservations(int user, String status)
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_RESERVATION+" WHERE user_id='"+user+"' AND status='"+status+"'", null);

        return cursor;
    }

    public Cursor getReservationById(int id)
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        ///Perform RawQuery
        Cursor cursor = sqldb.rawQuery("SELECT * FROM "+TABLE_RESERVATION+" WHERE ID='"+id+"'  ORDER BY fromDate", null);

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

    public boolean updateRoom(int numOfRooms, int hotel_id, String rType, String stat, int user, String checkin, String checkout, int resId)
    {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        boolean ret=false;
        if(numOfRooms!=0)
        {
            for(int i=0;i<numOfRooms;i++)   //reserve these rooms
            {
                Cursor cursor = sqldb.rawQuery("SELECT ID FROM "+TABLE_ROOM+ " WHERE hotel_id='"+hotel_id+"' AND roomType='"+rType+"' AND status='available' LIMIT 1", null);
                cursor.moveToFirst();
                int id=cursor.getInt(0);
                ContentValues cv=new ContentValues();
                cv.put("status", stat);
                cv.put("guest_id", user);
                cv.put("checkin", checkin);
                cv.put("checkout", checkout);
                cv.put("reservation_id", resId);

                int res=sqldb.update(TABLE_ROOM, cv, "ID="+id, null);
                if(res==0)
                {
                    ret=false;
                    Log.i("ROOM: ", "FAIL");
                }
                else
                {
                    ret=true;
                    //Log.i("ROOM: ", "PASS");
                }
            }
        }
        else
        {
            Log.i("NOW: ", "here!");
            //delete reserved rooms
            ContentValues cv=new ContentValues();
            cv.putNull("guest_id");
            cv.putNull("checkin");
            cv.putNull("checkout");
            cv.putNull("reservation_id");
            cv.put("status", "available");
            int res=sqldb.update(TABLE_ROOM, cv, "reservation_id="+resId, null);
            if(res==0)
            {
                ret=false;
                Log.i("ROOM: ", "FAIL");
            }
            else
            {
                ret=true;
                //Log.i("ROOM: ", "PASS");
            }
        }
        return ret;
    }

    public void alterAdd(String tableName, String colName, String colType)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("ALTER TABLE "+tableName+" ADD COLUMN "+colName+" "+colType+"");
    }
}

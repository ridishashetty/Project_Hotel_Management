package com.cse5324.projecthotel;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainAppScreenActivity extends AppCompatActivity {

    EditText userName,passWord;
    DatabaseHelper db;
    hotelDatabase hdb;
    String sUsername,sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        ///Default values for testtable i.e. system users
        db = new DatabaseHelper(this);
        //db.deleteFrom();

        String COL_1 = "USERNAME";
        String COL_2 = "PASSWORD";
        String COL_3 = "FIRSTNAME";
        String COL_4 = "LASTNAME";
        String COL_5 = "PHONE";
        String COL_6 = "EMAIL";
        String COL_7 = "ADDRESS";
        String COL_8 = "CITY";
        String COL_9 = "STATE";
        String COL_10 = "ZIPCODE";
        String COL_11 = "CREDITCARDNO";
        String COL_12 = "CREDITCARDEXPIRY";
        String COL_13 = "ROLE";
        Cursor cs = db.getUsers();
        if(cs.getCount()==0) //no data
        {
            Log.i("MSG:", "no data found");
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
            if(db.insertData(admin) == false)
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
            if(db.insertData(m1) == false)
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
            if(db.insertData(m2) == false)
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
            if(db.insertData(m3) == false)
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
            if(db.insertData(m4) == false)
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
            if(db.insertData(m5) == false)
            {}
        }
        else //data
        {
            int flag=0;
            while(cs.moveToNext())
            {
                if(cs.getString(1).equals("Tony") || cs.getString(1).equals("Evan"))
                {
                    flag=1;
                }
            }
            if(flag==0)
            {
                db.deleteFrom();
            }
           /* For debugging
           while(cs.moveToNext())
            {
                Log.i(cs.getString(0)+": ", cs.getString(1));
            }*/
        }db.close();

        ///Default Permanent room values for database
        hdb = new hotelDatabase(this);
        //hdb.deleteFrom();
        Cursor cursor = hdb.getRoom();
        if(cursor.getCount()==0)
        {
            for (int j = 1; j <= 5; j++)  //5 hotels
            {
                for (int k = 1; k <= 4; k++)  //4 floors
                {
                    for (int l = 1; l <= 25; l++)  //25 rooms per floor
                    {
                        ContentValues r = new ContentValues();
                        r.put("hotel_id", j);
                        if (l < 10) {
                            r.put("room_no", k + "0" + l);
                        } else {
                            r.put("room_no", k + "" + l);
                        }
                        if (k < 4) {
                            r.put("roomType", "Standard");
                            r.put("status", "unavailable");
                        } else if (k == 4) {
                            r.put("status", "available");
                            if (l <= 13) {
                                r.put("roomType", "Standard");
                            } else {
                                if (l <= 21) {
                                    r.put("roomType", "Deluxe");
                                } else {
                                    r.put("roomType", "Suite");
                                }
                            }
                        }
                        if (hdb.insertRoom(r) == false) {
                            Toast.makeText(this, "Error in rooms occurred", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Log.i("its: ", j + "-" + k + "" + l);
                        }
                    }
                }
            }
        }
        else
        {/*
            while(cursor.moveToNext())
            {
                Log.i("its: ", cursor.getString(2));
            }
            */
        }
        hdb.close();
    }

    public void loginCheck(View view) {

        userName = (EditText) findViewById(R.id.login_username);
        sUsername = userName.getText().toString().trim();

        passWord = (EditText) findViewById(R.id.login_password);
        sPassword = passWord.getText().toString().trim();

        if(TextUtils.isEmpty(sUsername) && TextUtils.isEmpty(sPassword))
        {
            Toast.makeText(this, "Both username and password must be entered", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(sUsername))
        {
            Toast.makeText(this, "Username must be entered", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(sPassword))
        {
            Toast.makeText(this, "Password must be entered", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Current page is Login Page
            db = new DatabaseHelper(this);

            Cursor cursor=db.ValidateUser(sUsername, sPassword);
            if(cursor.getCount()==0)
            {
                Toast.makeText(this, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(cursor.moveToFirst()) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                    String role = cursor.getString(13);
                    Log.i("msg: ", role);
                    if(role.equals("g"))
                    {
                        final Intent intent = new Intent(MainAppScreenActivity.this, GuestScreen.class);
                        intent.putExtra("user_id", cursor.getString(0));
                        startActivity(intent);
                    }
                    else if(role.equals("m"))
                    {
                        final Intent intent = new Intent(MainAppScreenActivity.this, ManagerScreen.class);
                        intent.putExtra("user_id", cursor.getString(0));
                        startActivity(intent);
                    }
                    else if(role.equals("a"))
                    {
                        final Intent intent = new Intent(MainAppScreenActivity.this, AdminScreen.class);
                        intent.putExtra("user_id", cursor.getString(0));
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }


    public void registerDetails(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
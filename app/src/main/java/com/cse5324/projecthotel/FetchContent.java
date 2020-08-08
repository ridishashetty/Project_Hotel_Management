package com.cse5324.projecthotel;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.PersistableBundle;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class FetchContent extends AppCompatActivity {
    hotelDatabase hdb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("View Selected Reservation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hdb = new hotelDatabase(this);
        int hid=0;
        String rt="";
        Intent i = getIntent();
        final String user_id = i.getStringExtra("user_id");
        final String room_id = i.getStringExtra("row_num");
        String lastPage = i.getStringExtra("from");

        if(lastPage.equals("summary")) {
            setContentView(R.layout.load_content);
        }
        else if(lastPage.equals("request")) {
            setContentView(R.layout.load_request_contents);
            Cursor cursor = hdb.getRoomById(room_id);
            if(cursor.getCount()==0)
            {
                Toast.makeText(FetchContent.this, "ERROR!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(cursor.moveToFirst())
                {
                    Toast.makeText(FetchContent.this, cursor.getString(2), Toast.LENGTH_SHORT).show();
                    //Log.i("msg: ", cursor.getColumnCount()+"");
                    hid = cursor.getInt(1); //hotel id
                    rt = cursor.getString(3).toLowerCase(); // room type
                    TextView date = findViewById(R.id.td1);
                    TextView time = findViewById(R.id.td2);

                    Calendar cal = Calendar.getInstance();
                    Date today = cal.getTime();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
                    date.setText(sdf1.format(today));
                    time.setText("12:00:00");
                    TextView type = findViewById(R.id.td3);
                    type.setText(rt);
                    TextView checkin = findViewById(R.id.td5);
                    checkin.setText(sdf1.format(today));
                    TextView checkout = findViewById(R.id.td6);

                    String now = today.toString();
                    Calendar c = Calendar.getInstance();
                    try{
                        c.setTime(sdf1.parse(now));
                    }
                    catch(ParseException e){ e.printStackTrace(); }
                    //Incrementing the date by 30
                    c.add(Calendar.DAY_OF_MONTH, 30);
                    checkout.setText(sdf1.format(c.getTime()));
                }
            }
        }
        else
        {
            //error
        }

        //Logout
        final Button button= findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(FetchContent.this, MainAppScreenActivity.class));
            }
        });
        //Request submit button
        final Button btn = findViewById(R.id.RequestReservation);
/*        btn.setOnClickListener(new View.OnClickListener() {
            TextView tv1 = findViewById(R.id.td5);
            TextView tv2 = findViewById(R.id.td6);
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("user_id", user_id);
                cv.put("fromDate", tv1.getText().toString());
                cv.put("toDate", tv2.getText().toString());
                cv.put("room_id", room_id);
                if(hdb.insertReservation(cv) == false)
                {
                    Toast.makeText(FetchContent.this, "Not Inserted to Reservation table", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ///alter database rooms to change status -> unavailable
                    hdb.updateRoomStatus("unavailable", room_id);
                    ///go to summary page
                    final Intent intent = new Intent(FetchContent.this, ReservationSummary.class);
                    intent.putExtra("user_id", user_id);
                    startActivity(intent);
                }
            }
        }); */

        //Toast.makeText(FetchContent.this, row_id, Toast.LENGTH_SHORT).show();

        int id = getResources().getIdentifier(rt+hid, "drawable", getPackageName());
        Drawable draw = getResources().getDrawable(id);
        findViewById(R.id.picture).setBackground(draw);

        ////editable
        final TableLayout tbl = findViewById(R.id.tabLay);
        tbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(FetchContent.this, GuestProfile.class));
               /* for(int i = 1; i<=7; i++)
                {
                    String a = "td"+i;
                    findViewById(Integer.valueOf(a)).setCursorVisible(true);
                    findViewById(Integer.valueOf(a)).setFocusableInTouchMode(true);
                    findViewById(Integer.valueOf(a)).setInputType(InputType.TYPE_CLASS_TEXT);
                    findViewById(Integer.valueOf(a)).requestFocus();
                }*/
            }
        });
    }
    //go back button to work
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

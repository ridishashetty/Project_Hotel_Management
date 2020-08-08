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
import android.widget.EditText;
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

        Intent i = getIntent();
        final String user_id = i.getStringExtra("user_id");
        int hid=i.getIntExtra("hotelID", 0);
        int numOfRoom=i.getIntExtra("numRoom", 1);
        String rt=i.getStringExtra("roomType");
        //final String room_id = i.getStringExtra("row_num");
        String lastPage = i.getStringExtra("from");

        if(lastPage.equals("summary")) {
            setContentView(R.layout.load_content);
            Toast.makeText(FetchContent.this, lastPage, Toast.LENGTH_SHORT).show();
        }
        else if(lastPage.equals("request"))
        {
            setContentView(R.layout.load_request_contents);
            //Cursor cursor = hdb.getRoomById(room_id);
            //Toast.makeText(FetchContent.this, cursor.getString(2), Toast.LENGTH_SHORT).show();
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

            TextView numR=findViewById(R.id.td4);
            numR.setText(Integer.toString(numOfRoom));

            String now = today.toString();
            Calendar c = Calendar.getInstance();
            try{
                c.setTime(sdf1.parse(now));
            }
            catch(ParseException e){ e.printStackTrace(); }
            //Incrementing the date by 30
            c.add(Calendar.DAY_OF_MONTH, 1);
            checkout.setText(sdf1.format(c.getTime()));
            //Image load
            int id = getResources().getIdentifier(rt.toLowerCase()+hid, "drawable", getPackageName());
            Drawable draw = getResources().getDrawable(id);
            findViewById(R.id.picture).setBackground(draw);

            //Request submit button
            final Button btn = findViewById(R.id.RequestReservation);
            btn.setOnClickListener(new View.OnClickListener() {
                TextView strTime=findViewById(R.id.td2);
                TextView hotel=findViewById(R.id.td);
                TextView rType=findViewById(R.id.td3);
                TextView nOfr=findViewById(R.id.td4);
                EditText from = findViewById(R.id.td5);
                EditText tod = findViewById(R.id.td6);
                TextView price=findViewById(R.id.td7);

                @Override
                public void onClick(View v) {
                    ContentValues cv = new ContentValues();
                    cv.put("user_id", user_id);
                    cv.put("fromDate", from.getText().toString());
                    cv.put("toDate", tod.getText().toString());
                    //cv.put("room_id", room_id);
                    if(hdb.insertReservation(cv) == false)
                    {
                        Toast.makeText(FetchContent.this, "Not Inserted to Reservation table", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        ///go to summary page
                        final Intent intent = new Intent(FetchContent.this, ReservationSummary.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("inTime", strTime.getText());
                        intent.putExtra("hotel", hotel.getText());
                        intent.putExtra("rType", rType.getText());
                        intent.putExtra("numRooms", nOfr.getText());
                        intent.putExtra("checkin", from.getText());
                        intent.putExtra("checkout", tod.getText());
                        intent.putExtra("amount", price.getText());
                        startActivity(intent);
                    }
                }
            });
        }
        else
        {
            Toast.makeText(FetchContent.this, "Where is this coming from?", Toast.LENGTH_SHORT).show();
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
        //Toast.makeText(FetchContent.this, row_id, Toast.LENGTH_SHORT).show();

        ////editable
        final TableLayout tbl = findViewById(R.id.tabLay);
    /*    tbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(FetchContent.this, GuestProfile.class));
                for(int i = 1; i<=7; i++)
                {
                    String a = "td"+i;
                    findViewById(Integer.valueOf(a)).setCursorVisible(true);
                    findViewById(Integer.valueOf(a)).setFocusableInTouchMode(true);
                    findViewById(Integer.valueOf(a)).setInputType(InputType.TYPE_CLASS_TEXT);
                    findViewById(Integer.valueOf(a)).requestFocus();
                }
            }
        }); */
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

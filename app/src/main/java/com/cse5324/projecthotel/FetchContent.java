package com.cse5324.projecthotel;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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
import java.util.Locale;

public class FetchContent extends AppCompatActivity {
    hotelDatabase hdb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("View Selected Reservation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        final String user_id = i.getStringExtra("user_id");
        final String user = i.getStringExtra("user_id");
        int hid=i.getIntExtra("hotelID", 1);
        String lastPage = i.getStringExtra("from");
        Log.i("==============", user_id);

        if(lastPage.equals("summary")) {
            hdb = new hotelDatabase(this);
            setContentView(R.layout.load_content);
            //Toast.makeText(FetchContent.this, lastPage, Toast.LENGTH_SHORT).show();

            int reserveID=Integer.parseInt(i.getStringExtra("reserveID"));
            Cursor c=hdb.getReservationById(reserveID);
            c.moveToNext();

            TextView td1=findViewById(R.id.td1);
            td1.setText(c.getString(5));

            TextView time=findViewById(R.id.td2);
            time.setText(c.getString(7));

            TextView hotel=findViewById(R.id.td);
            Cursor git=hdb.getHotel(c.getString(3));
            git.moveToNext();
            hotel.setText(git.getString(2));

            TextView rType=findViewById(R.id.td3);
            rType.setText(c.getString(4));

            TextView nor=findViewById(R.id.td4);
            nor.setText(c.getString(2));

            TextView in=findViewById(R.id.td5);
            in.setText(c.getString(5));

            TextView out=findViewById(R.id.td6);
            out.setText(c.getString(6));

            final TextView price=findViewById(R.id.td7); //get current
            //Room price
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            String itis="";

            switch (day) {
                case Calendar.SUNDAY:
                case Calendar.SATURDAY:
                    itis="weekend";
                    break;
                default:
                    itis="weekday";
                    break;
            }

            String low=c.getString(4).toLowerCase();
            Cursor amt = hdb.getPrice(low, itis);
            if(amt.getCount()!=0)
            {
                amt.moveToFirst();
                int cal=amt.getInt(3)*Integer.parseInt(c.getString(2));
                price.setText(amt.getInt(3)+" dollars");
            }

            int id = getResources().getIdentifier(low+hid, "drawable", getPackageName());
            Drawable draw = getResources().getDrawable(id);
            findViewById(R.id.picture).setBackground(draw);

            final Button confirm= findViewById(R.id.confirm);
            if(c.getString(c.getColumnIndex("status")).equals("confirmed"))
            {
                confirm.setText("CANCEL RESERVATION");
                confirm.setBackgroundColor(Color.GRAY);
            }
            else
            {
                confirm.setText("CONFIRM RESERVATION");
                confirm.setBackgroundColor(Color.BLUE);
            }

            final int reserve=reserveID;
            final String u=user_id;
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String btn=confirm.getText().toString();
                    EditText et=findViewById(R.id.pin);
                    if(et.getText().toString().equals("")) {
                        if (btn.toLowerCase().contains("confirm")) {
                            TableRow trPop = findViewById(R.id.pop);
                            trPop.setVisibility(View.VISIBLE);
                            trPop.setBackgroundColor(Color.CYAN);
                        }
                        else if(btn.toLowerCase().contains("cancel"))
                        {
                            if(hdb.updateReservation(reserve,0,0, btn))
                            {
                                Log.i("OP: ", "Deletion success!"+user);
                                Toast.makeText(FetchContent.this, "Reservation is cancelled", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(FetchContent.this, ReservationSummary.class);
                                intent.putExtra("user_id", user);
                                startActivity(intent);
                            }
                        }
                    }
                    else if(et.getText().toString().matches("\\d+"))    //go ahead
                    {
                        if(et.getText().toString().length()==3) //valid
                        {
                            String amt=price.getText().toString().split(" dollar")[0];
                            if(hdb.updateReservation(reserve, Integer.parseInt(amt), Integer.parseInt(et.getText().toString()), btn))
                            {
                                Log.i("OP: ", "Update success!"+user);
                                Toast.makeText(FetchContent.this, "Reservation is confirmed", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FetchContent.this, ReservationSummary.class);
                                intent.putExtra("user_id", user);
                                startActivity(intent);
                            }
                            else
                            {
                                Log.i("OP: ", "Update fail!");
                            }
                        }
                        else
                        {
                            Toast.makeText(FetchContent.this, "Find 3-digit CVV in the back of your card", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(FetchContent.this, "Invalid Pin", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            hdb.close();
        }
        else if(lastPage.equals("request"))
        {
            hdb = new hotelDatabase(this);
            int numOfRoom=i.getIntExtra("numRoom", 1);
            String rt=i.getStringExtra("roomType");
            String price=i.getStringExtra("cost");
            Log.i("fetch::::::::::::", price);
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
            final TextView checkin = findViewById(R.id.td5);
            checkin.setText(sdf1.format(today));
            final TextView checkout = findViewById(R.id.td6);

            TextView numR=findViewById(R.id.td4);
            numR.setText(Integer.toString(numOfRoom));

            String newD= sdf1.format(today);
            String now = newD.toString();
            Calendar c = Calendar.getInstance();
            try{
                c.setTime(sdf1.parse(now));
            }
            catch(ParseException e){ e.printStackTrace(); }
            //Incrementing the date by 1
            c.add(Calendar.DAY_OF_MONTH, 1);
            checkout.setText(sdf1.format(c.getTime()));
            //Image load
            int id = getResources().getIdentifier(rt.toLowerCase()+hid, "drawable", getPackageName());
            Drawable draw = getResources().getDrawable(id);
            findViewById(R.id.picture).setBackground(draw);

            TextView amt=findViewById(R.id.td7);
            amt.setText(Integer.parseInt(price)+" dollars");
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
                    final String user=user_id;
                    ContentValues cv = new ContentValues();
                    cv.put("user_id", user);
                    cv.put("fromDate", from.getText().toString());
                    cv.put("toDate", tod.getText().toString());
                    cv.put("amount", price.getText().toString());
                    cv.put("inTime", strTime.getText().toString());
                    Cursor cs = hdb.getHotel(hotel.getText().toString());
                    String hotID = "";
                    if (cs.getCount() == 0) {
                        Log.i("ERR:", "error");
                    } else {
                        cs.moveToFirst();
                        hotID = cs.getString(0);
                        //Log.i("ERR:", num);
                    }
                    cv.put("hotel_id", hotID);      ////////ID
                    cv.put("rType", rType.getText().toString());
                    cv.put("status", "pending");
                    cv.put("numRooms", nOfr.getText().toString());
                    //cv.put("room_id", room_id);
                    if(hdb.insertReservation(cv) == false)
                    {
                        Toast.makeText(FetchContent.this, "Not Inserted to Reservation table", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        /*Log.i("==============", user_id+"|||"+from.getText().toString()+"|||"+tod.getText().toString()+
                                "\n"+price.getText().toString()+"|||"+strTime.getText().toString()+"|||"+hotID+"|||"+
                                rType.getText().toString()+"|||"+nOfr.getText().toString());
                         */
                        ///go to summary page
                        final Intent intent = new Intent(FetchContent.this, ReservationSummary.class);
                        intent.putExtra("from", "fetch");
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("inTime", strTime.getText());
                        intent.putExtra("hotel", hotel.getText());
                        intent.putExtra("rType", rType.getText());
                        intent.putExtra("numRoom", nOfr.getText());
                        intent.putExtra("checkin", from.getText().toString());
                        intent.putExtra("checkout", tod.getText().toString());
                        intent.putExtra("amount", price.getText());
                        //Log.i("HEREEEE::: ", checkin.getText()+" "+checkout.getText());
                        startActivity(intent);
                    }
                }
            });
            hdb.close();
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

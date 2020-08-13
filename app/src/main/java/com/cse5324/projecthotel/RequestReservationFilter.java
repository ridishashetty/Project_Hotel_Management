package com.cse5324.projecthotel;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class RequestReservationFilter extends AppCompatActivity {
    hotelDatabase hdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_reserve_filter);

        Intent getI = getIntent();
        final String info = getI.getStringExtra("user_id");

        //cross button
        final Button end=findViewById(R.id.cross);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //HOTEL NAME toggle
        final Button b1=findViewById(R.id.hotelName);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.RGName).getVisibility()==View.GONE)
                {
                    findViewById(R.id.RGName).setVisibility(View.VISIBLE);
                }
                else
                {
                    findViewById(R.id.RGName).setVisibility(View.GONE);
                }

            }
        });

        //ROOM TYPE toggle
        final Button b2=findViewById(R.id.roomType);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.RGType).getVisibility()==View.GONE)
                {
                    findViewById(R.id.RGType).setVisibility(View.VISIBLE);
                }
                else
                {
                    findViewById(R.id.RGType).setVisibility(View.GONE);
                }
            }
        });

        //Number of Rooms toggle
        final Button b3=findViewById(R.id.noOfRooms);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.RGRooms).getVisibility()==View.GONE)
                {
                    findViewById(R.id.RGRooms).setVisibility(View.VISIBLE);
                }
                else
                {
                    findViewById(R.id.RGRooms).setVisibility(View.GONE);
                }
            }
        });

        //Start Date toggle
        final Button b4=findViewById(R.id.startDate);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.date).getVisibility()==View.GONE)
                {
                    findViewById(R.id.date).setVisibility(View.VISIBLE);
                }
                else
                {
                    findViewById(R.id.date).setVisibility(View.GONE);
                }
            }
        });

        //Start Date toggle
        final Button b5=findViewById(R.id.startTime);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.time).getVisibility()==View.GONE)
                {
                    findViewById(R.id.time).setVisibility(View.VISIBLE);
                }
                else
                {
                    findViewById(R.id.time).setVisibility(View.GONE);
                }
            }
        });

        final Button load = findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rgName=findViewById(R.id.RGName);
                int name_i=rgName.getCheckedRadioButtonId();
                RadioButton hotel=findViewById(name_i);
                String hotel_name=hotel.getText().toString();
                hdb=new hotelDatabase(RequestReservationFilter.this);
                Cursor cs=hdb.getHotel(hotel_name);
                cs.moveToNext();
                int hotel_id=cs.getInt(0);
                //Toast.makeText(RequestReservationFilter.this, Integer.toString(hotel_id), Toast.LENGTH_SHORT).show();

                CheckBox cb1, cb2, cb3;
                cb1=findViewById(R.id.type1);
                cb2=findViewById(R.id.type2);
                cb3=findViewById(R.id.type3);
                List <String>type=new ArrayList<String>();
                if(cb1.isChecked())
                {
                    type.add(cb1.getText().toString());
                }
                if(cb2.isChecked())
                {
                    type.add(cb2.getText().toString());
                }
                if(cb3.isChecked())
                {
                    type.add(cb3.getText().toString());
                }
                String rType=type.toString();
                //Toast.makeText(RequestReservationFilter.this, rType, Toast.LENGTH_SHORT).show();

                RadioGroup rgRoom=findViewById(R.id.RGRooms);
                int room_i=rgRoom.getCheckedRadioButtonId();
                RadioButton room=findViewById(room_i);
                String numRoom=room.getText().toString();
                int numOfRooms=Integer.parseInt(numRoom.split(" room")[0]);
                //Toast.makeText(RequestReservationFilter.this, Integer.toString(numOfRooms), Toast.LENGTH_SHORT).show();
                //finish();

                DatePicker date=findViewById(R.id.date);
                int day=date.getDayOfMonth();
                int month=date.getMonth();
                int year=date.getYear();
                //String chosenDate=month+1+"-"+day+"-"+year;
                //Toast.makeText(RequestReservationFilter.this, chosenDate, Toast.LENGTH_SHORT).show();
                Calendar cl= Calendar.getInstance();
                cl.set(Calendar.DAY_OF_MONTH, day);
                cl.set(Calendar.MONTH, month);
                cl.set(Calendar.YEAR, year);
                SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-YYYY");
                String chosenDate=sdf.format(cl.getTime());
                //Toast.makeText(RequestReservationFilter.this, chosenDate, Toast.LENGTH_SHORT).show();

                TimePicker time=findViewById(R.id.time);
                int hour=time.getCurrentHour();
                int min=time.getCurrentMinute();
                String chosenTime=hour+":"+min;
                //Toast.makeText(RequestReservationFilter.this, hour+"=="+min, Toast.LENGTH_SHORT).show();

                if(type.size()!=0) {
                    Intent intent = new Intent(RequestReservationFilter.this, RequestReservations.class);
                    intent.putExtra("from", "filter");
                    intent.putExtra("user_id", info);
                    intent.putExtra("hotel_id", hotel_id);
                    intent.putExtra("hotel_name", hotel_name);
                    intent.putExtra("roomType", rType);
                    intent.putExtra("nOfRoom", numOfRooms);
                    intent.putExtra("date", chosenDate);
                    intent.putExtra("time", chosenTime);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(RequestReservationFilter.this, "You must select atleast 1 roomType to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
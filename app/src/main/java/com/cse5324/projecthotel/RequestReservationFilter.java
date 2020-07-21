package com.cse5324.projecthotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RequestReservationFilter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_reserve_filter);

        //getSupportActionBar().setTitle(""); // for set actionbar title

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
    }
}
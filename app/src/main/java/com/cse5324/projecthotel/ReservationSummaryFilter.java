package com.cse5324.projecthotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReservationSummaryFilter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_summ_filter);

        //getSupportActionBar().setTitle(""); // for set actionbar title

        //cross button
        final Button end=findViewById(R.id.cross);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

        //Start Time toggle
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

        //submit button
        final Button submit=findViewById(R.id.load);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

package com.cse5324.projecthotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReservationSummaryFilter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_summ_filter);

        //getSupportActionBar().setTitle(""); // for set actionbar title
        Intent intent=getIntent();
        final String info=intent.getStringExtra("user_id");
        //Log.i("user??", info);

        //cross button
        Button end=findViewById(R.id.end);
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
        Button submit=findViewById(R.id.submit);
        final String user=info;
//        Log.i("user??", user);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                TimePicker time= findViewById(R.id.time);
                int hour=time.getCurrentHour();
                int min=time.getCurrentMinute();
                String chosenTime=hour+":"+min;

                Intent i=new Intent(ReservationSummaryFilter.this, ReservationSummary.class);
                i.putExtra("startDate", chosenDate);
                i.putExtra("startTime", chosenTime);
                i.putExtra("user_id", user);
                i.putExtra("from", "filter");
                startActivity(i);
            }
        });
    }
}

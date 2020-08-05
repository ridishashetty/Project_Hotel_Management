package com.cse5324.projecthotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ViewReservations extends AppCompatActivity {

    Spinner spinner1;
    String date="",time="",hotel="",activity="";

    EditText reservation_Date,reservation_Time,reservation_hotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reservations);

        getSupportActionBar().setTitle("View Reservations");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //go back button

        Intent intent = getIntent();
        activity = intent.getStringExtra("activity");


        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Maverick");
        spinnerArray.add("A");
        spinnerArray.add("B");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1 = (Spinner) findViewById(R.id.reservation_hotels);
        spinner1.setAdapter(adapter);

        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        time = java.text.DateFormat.getTimeInstance().format(new Date());
        time = time.substring(0, Math.min(time.length(),4));

        reservation_Date = (EditText)findViewById(R.id.reservation_Date);
        reservation_Date.setText(date);

        reservation_Time = (EditText)findViewById(R.id.reservation_Time);
        reservation_Time.setText(time);
    }
    public void viewReserve(View view)
    {
        hotel = (String) spinner1.getSelectedItem();

        Intent myIntent = new Intent(this, ReservationList.class);
        myIntent.putExtra("time",time);
        myIntent.putExtra("date",date);
        myIntent.putExtra("hotel",hotel);
        myIntent.putExtra("activity",activity);
        startActivity(myIntent);
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

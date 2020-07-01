package com.cse5324.projecthotel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ViewReservations extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reservations);

        getSupportActionBar().setTitle("View Reservations");
    }
}

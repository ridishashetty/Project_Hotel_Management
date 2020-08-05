package com.cse5324.projecthotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GuestScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_screen);

        getSupportActionBar().setTitle("Guest Home");
        //What's the user id?
        Intent i = getIntent();
        final String info = i.getStringExtra("user_id");
        Toast.makeText(GuestScreen.this, "user id: "+info, Toast.LENGTH_SHORT).show();

        //Logout
        final Button button= findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(GuestScreen.this, MainAppScreenActivity.class));
            }
        });

        final Button b1=findViewById(R.id.profile);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Profile page
                //Toast.makeText(GuestScreen.this, "My Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(GuestScreen.this, GuestProfile.class));
            }
        });

        final Button b2=findViewById(R.id.request);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Request Reservation page
                //Toast.makeText(GuestScreen.this, "Request Hotel Reservations", Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent(GuestScreen.this, RequestReservations.class);
                intent.putExtra("user_id", info);
                startActivity(intent);
            }
        });

        final Button b3=findViewById(R.id.confirmReserve);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Summary page
                //Toast.makeText(GuestScreen.this, "View Reservations Summary", Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent(GuestScreen.this, ReservationSummary.class);
                intent.putExtra("user_id", info);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text= parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        String lower=text.toLowerCase();
        Intent intent;
        if (lower.contains("profile")) {
            //startActivity(new Intent(this, ___.class));
            Toast.makeText(parent.getContext(), lower, Toast.LENGTH_SHORT).show();
        }
        else if (lower.contains("reservation")) {
            //startActivity(new Intent(this, ___.class));
            Toast.makeText(parent.getContext(), lower, Toast.LENGTH_SHORT).show();
        }
        else if (lower.contains("summary")) {
            //startActivity(new Intent(this, ___.class));
        }
        else if (lower.contains("pending")) {
            //startActivity(new Intent(this, ___.class));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

package com.cse5324.projecthotel;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_screen);

        getSupportActionBar().setTitle("Manager Home"); // for set actionbar title

        Spinner spinner = (Spinner) findViewById(R.id.spinnerM);
        //To get values from the array created in strings.xml and send to the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.manager_functions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Logout
        final Button button= findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(ManagerScreen.this, MainActivity.class));
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
            startActivity(new Intent(this, ManagerProfile.class));
        }
        else if (lower.contains("reservation")) {
            startActivity(new Intent(this, ViewReservations.class));
        }
        else if (lower.contains("search")) {
            startActivity(new Intent(this, SearchRoom.class));
        }
        else if (lower.contains("available")) {
            startActivity(new Intent(this, AvailableRoom.class));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

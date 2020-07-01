package com.cse5324.projecthotel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_profile);

        getSupportActionBar().setTitle("My Profile");
    }
}

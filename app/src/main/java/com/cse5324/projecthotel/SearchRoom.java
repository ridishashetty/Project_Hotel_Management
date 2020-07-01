package com.cse5324.projecthotel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SearchRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_room);

        getSupportActionBar().setTitle("Search for a room");
    }
}

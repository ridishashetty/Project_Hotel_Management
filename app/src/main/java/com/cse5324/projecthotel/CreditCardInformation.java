package com.cse5324.projecthotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreditCardInformation extends AppCompatActivity {
    String text = "";
    EditText CreditCardPIN;
    Button ConfirmRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_information);

        getSupportActionBar().setTitle("Credit Card Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        text = getIntent().getExtras().getString("listselected");

        CreditCardPIN = (EditText)findViewById(R.id.CreditCardPIN);

        ConfirmRes = (Button)findViewById(R.id.ConfirmRes);

        ConfirmRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CreditCardInformation.this, ConfirmReservation.class);
                myIntent.putExtra("listselected",text);
                startActivity(myIntent);
            }
        });

    }
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
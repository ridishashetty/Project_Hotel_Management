package com.cse5324.projecthotel;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FetchContent extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("View Selected Reservation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String result = i.getStringExtra("value");
        String lastPage = i.getStringExtra("from");
        /*final TextView tv = (TextView) findViewById(R.id.output);
        tv.setText(lastPage+" here");*/
        if(lastPage.equals("summary")) {
            setContentView(R.layout.load_content);
        }
        else if(lastPage.equals("request")) {
            setContentView(R.layout.load_request_contents);
        }
        else
        {
            //error
        }

        //Logout
        final Button button= findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(FetchContent.this, MainAppScreenActivity.class));
            }
        });

        int id = getResources().getIdentifier("r"+result, "drawable", getPackageName());
        Drawable draw = getResources().getDrawable(id);
        findViewById(R.id.picture).setBackground(draw);

        ////editable
        final TableLayout tbl = findViewById(R.id.tabLay);
        tbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(FetchContent.this, GuestProfile.class));
               /* for(int i = 1; i<=7; i++)
                {
                    String a = "td"+i;
                    findViewById(Integer.valueOf(a)).setCursorVisible(true);
                    findViewById(Integer.valueOf(a)).setFocusableInTouchMode(true);
                    findViewById(Integer.valueOf(a)).setInputType(InputType.TYPE_CLASS_TEXT);
                    findViewById(Integer.valueOf(a)).requestFocus();
                }*/
            }
        });
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

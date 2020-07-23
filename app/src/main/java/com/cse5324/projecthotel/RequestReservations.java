package com.cse5324.projecthotel;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RequestReservations extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_reservations);

        getSupportActionBar().setTitle("Request Hotel Reservations");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //go back button

        //Logout
        final Button button= findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(RequestReservations.this, MainAppScreenActivity.class));
            }
        });
        //OpenMenu
        final Button btnMenu= findViewById(R.id.filterMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(RequestReservations.this, RequestReservationFilter.class));
            }
        });

        //Load Page contents
        View linearLayout = findViewById(R.id.page);
        ScrollView sv = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);

        //Structure
        TableLayout table = new TableLayout(this);
        table.setBackgroundColor(Color.parseColor("#000000"));
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        table.setLayoutParams(param);
        param.setMargins(5,5,5,5);

        for(int i=1;i<6;i++)
        {
            TableRow trMain = new TableRow(this);
            trMain.setId(R.id.reqRow+i);
            trMain.setBackgroundColor(Color.parseColor("#ffffff"));
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT
            );
            params.setMargins(5,3,5,3);
            trMain.setLayoutParams(params);
            trMain.setWeightSum(1f);
            //picture load
            int id = getResources().getIdentifier("r"+i, "drawable", getPackageName());
            Drawable draw = getResources().getDrawable(id);
            //ImageView pic = findViewById(R.id.roomPicture);
            ImageView pic = new ImageView(this);
            pic.setBackground(draw);

           // int width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0,getResources().getDisplayMetrics()));
           // int height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0,getResources().getDisplayMetrics()));
            final float scale = getResources().getDisplayMetrics().density;
            int pixels = (int) (170 * scale + 0.5f);

            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(0,
                    pixels);
            layoutParams.weight = 0.35f;
            pic.setLayoutParams(layoutParams);

            ////

            TableLayout miniTable = new TableLayout(this);
            TableRow.LayoutParams p = new TableRow.LayoutParams(
                    0,
                    TableRow.LayoutParams.MATCH_PARENT
            );
            p.weight = 0.65f;
            p.setMargins(10,5,5,0);
            miniTable.setLayoutParams(p);

            //All Details:
       /*     TableRow tr0 = new TableRow(this);
            TextView th0 = new TextView(this);
            th0.setTypeface(null, Typeface.BOLD);
            th0.setTextSize(12);
            th0.setText(""+i);
            tr0.addView(th0);
            miniTable.addView(tr0); */

            //Hotel Name
            TableRow tr1 = new TableRow(this);
            TextView th1 = new TextView(this);
            th1.setTypeface(null, Typeface.BOLD);
            th1.setText("Hotel Name: ");
            th1.setTextSize(12);
            TextView td1 = new TextView(this);
            td1.setText("Maverick");        //get hotel name from db
            td1.setTextSize(12);
            tr1.addView(th1);
            tr1.addView(td1);
            miniTable.addView(tr1);

            //No. of rooms
            TableRow tr2 = new TableRow(this);
            TextView th2 = new TextView(this);
            th2.setTypeface(null, Typeface.BOLD);
            th2.setText("Number of Rooms: ");
            th2.setTextSize(12);
            TextView td2 = new TextView(this);
            td2.setText("1 room");        //get room type from db
            td2.setTextSize(12);
            tr2.addView(th2);
            tr2.addView(td2);
            miniTable.addView(tr2);

            //Room Type
            TableRow tr3 = new TableRow(this);
            TextView th3 = new TextView(this);
            th3.setTypeface(null, Typeface.BOLD);
            th3.setText("Room Type: ");
            th3.setTextSize(12);
            TextView td3 = new TextView(this);
            td3.setText("Standard");        //get bed from db
            td3.setTextSize(12);
            tr3.addView(th3);
            tr3.addView(td3);
            miniTable.addView(tr3);

            //Start Date
            TableRow tr4 = new TableRow(this);
            TextView th4 = new TextView(this);
            th4.setTypeface(null, Typeface.BOLD);
            th4.setText("Arrival Date: ");
            th4.setTextSize(12);
            TextView td4 = new TextView(this);
            td4.setText("07-27-2020");        //get date from db
            td4.setTextSize(12);
            tr4.addView(th4);
            tr4.addView(td4);
            miniTable.addView(tr4);

            //number of days stay available
            TableRow tr5 = new TableRow(this);
            TextView th5 = new TextView(this);
            th5.setTypeface(null, Typeface.BOLD);
            th5.setText("Number of Nights: ");
            th5.setTextSize(12);
            TextView td5 = new TextView(this);
            td5.setText("5");        //get time from db
            td5.setTextSize(12);
            tr5.addView(th5);
            tr5.addView(td5);
            miniTable.addView(tr5);

            //Room price
            TableRow tr6 = new TableRow(this);
            TextView th6 = new TextView(this);
            th6.setTypeface(null, Typeface.BOLD);
            th6.setText("Total Price: ");
            th6.setTextSize(12);
            TextView td6 = new TextView(this);
            td6.setText("100 dollars");        //get time from db
            td6.setTextSize(12);
            tr6.addView(th6);
            tr6.addView(td6);
            miniTable.addView(tr6);

            table.addView(trMain);
            trMain.addView(pic);
            trMain.addView(miniTable);
        }
        ll.addView(table);
        sv.addView(ll);
        ((LinearLayout) linearLayout).addView(sv);

        ////Clickable rows
        for(int i=1;i<6;i++){
            final int n = i;
            TableRow t= findViewById(R.id.reqRow+i);
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RequestReservations.this, FetchContent.class);
                    intent.putExtra("value", Integer.toString(n));
                    intent.putExtra("from", "request");
                    startActivity(intent);
                }
            });
        }
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

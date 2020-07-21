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

public class ReservationSummary extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_reservations);

        getSupportActionBar().setTitle("View Reservation Summary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //go back button

        //Logout
        final Button button= findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(ReservationSummary.this, MainAppScreenActivity.class));
            }
        });
        //OpenMenu
        final Button btnMenu= findViewById(R.id.filterMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes to Login page
                startActivity(new Intent(ReservationSummary.this, ReservationSummaryFilter.class));
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

        for(int i=1;i<4;i++)
        {
            TableRow trMain = new TableRow(this);
            trMain.setId(View.generateViewId());
            trMain.setBackgroundColor(Color.parseColor("#ffffff"));
            trMain.callOnClick();
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT
            );
            params.setMargins(5,3,5,3);
            trMain.setLayoutParams(params);
            trMain.setWeightSum(1f);
            //picture load
            int id = getResources().getIdentifier("h"+i, "drawable", getPackageName());
            Drawable draw = getResources().getDrawable(id);
            //ImageView pic = findViewById(R.id.roomPicture);
            ImageView pic = new ImageView(this);
            pic.setBackground(draw);

            // int width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0,getResources().getDisplayMetrics()));
            // int height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,0,getResources().getDisplayMetrics()));
            final float scale = getResources().getDisplayMetrics().density;
            int pixels = (int) (150 * scale + 0.5f);

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

            //id
            TableRow tr = new TableRow(this);
            TextView th = new TextView(this);
            th.setTypeface(null, Typeface.BOLD);
            th.setText("n: ");
            th.setTextSize(12);
            TextView td = new TextView(this);
            td.setTextSize(12);
            tr.addView(th);
            tr.addView(td);
            miniTable.addView(tr);

            //Start Date
            TableRow tr0 = new TableRow(this);
            TextView th0 = new TextView(this);
            th0.setTypeface(null, Typeface.BOLD);
            th0.setText("Start Date: ");
            th0.setTextSize(12);
            TextView td0 = new TextView(this);
            td0.setText("<dateFrom db>");        //get date from db
            td0.setTextSize(12);
            tr0.addView(th0);
            tr0.addView(td0);
            miniTable.addView(tr0);

            //Start Time
            TableRow tr1 = new TableRow(this);
            TextView th1 = new TextView(this);
            th1.setTypeface(null, Typeface.BOLD);
            th1.setText("Start Time: ");
            th1.setTextSize(12);
            TextView td1 = new TextView(this);
            td1.setText("<timeFrom db>");        //get date from db
            td1.setTextSize(12);
            tr1.addView(th1);
            tr1.addView(td1);
            miniTable.addView(tr1);

            //Hotel Name
            TableRow tr2 = new TableRow(this);
            TextView th2 = new TextView(this);
            th2.setTypeface(null, Typeface.BOLD);
            th2.setText("Hotel Name: ");
            th2.setTextSize(12);
            TextView td2 = new TextView(this);
            td2.setText("Maverick");        //get hotel name from db
            td2.setTextSize(12);
            tr2.addView(th2);
            tr2.addView(td2);
            miniTable.addView(tr2);

            //No. of rooms
            TableRow tr3 = new TableRow(this);
            TextView th3 = new TextView(this);
            th3.setTypeface(null, Typeface.BOLD);
            th3.setText("Number of Rooms: ");
            th3.setTextSize(12);
            TextView td3 = new TextView(this);
            td3.setText("1 room");        //get room type from db
            td3.setTextSize(12);
            tr3.addView(th3);
            tr3.addView(td3);
            miniTable.addView(tr3);

            //Arrival Time
            TableRow tr4 = new TableRow(this);
            TextView th4 = new TextView(this);
            th4.setTypeface(null, Typeface.BOLD);
            th4.setText("Arrival Time: ");
            th4.setTextSize(12);
            TextView td4 = new TextView(this);
            td4.setText("<timeFrom db>");        //get date from db
            td4.setTextSize(12);
            tr4.addView(th4);
            tr4.addView(td4);
            miniTable.addView(tr4);

            table.addView(trMain);
            trMain.addView(pic);
            trMain.addView(miniTable);
        }
        ll.addView(table);
        sv.addView(ll);
        ((LinearLayout) linearLayout).addView(sv);
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

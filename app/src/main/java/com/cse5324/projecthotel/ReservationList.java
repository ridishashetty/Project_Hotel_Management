package com.cse5324.projecthotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservationList extends AppCompatActivity {
    String date="",time="",hotel="",activity="";
    private ArrayList<HashMap<String, String>> list;
    TextView startdate,hotelname,numberofrooms,checkindate,roomtype;
    ArrayList<Person> listreserve;
    String[] string1 = new String[]
            {
                    "7-24-2020","12:00:00","Maverick","100","7-24-2020","7-26-2020","Suite","120"
            };
    String[] string2 = new String[]
            {
                    "7-24-2020","12:00:00","Ranger","100","7-24-2020","7-26-2020","Deluxe","100"
            };
    String[] string3 = new String[]
            {
                    "7-24-2020","12:00:00","Liberty","100","7-24-2020","7-26-2020","Standard","80"
            };
    ListView listContent;
    DatabaseHelper db;
    ArrayAdapter<HashMap<String, String>> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);


        getSupportActionBar().setTitle("Reservation List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        time = getIntent().getExtras().getString("time");
        date = getIntent().getExtras().getString("date");
        hotel = getIntent().getExtras().getString("hotel");
        activity = getIntent().getExtras().getString("activity");

        listContent = (ListView)findViewById(R.id.thelist);

        db = new DatabaseHelper(this);
        //Person first = new Person("7-24-2020","12:00:00","Maverick","100","7-24-2020","7-26-2020","Suite","120");
        //Person second = new Person("7-24-2020","12:00:00","Ranger","100","7-24-2020","7-26-2020","Deluxe","100");
        //Person third = new Person("7-24-2020","12:00:00","Liberty","100","7-24-2020","7-26-2020","Standard","80");
        list=new ArrayList<HashMap<String,String>>();

        HashMap<String,String> temp=new HashMap<String, String>();
        temp.put("StartDate","7-24-2020");
        temp.put("StartTime","12:00:00");
        temp.put("HotelName","Maverick");
        temp.put("NumberofRooms","4");
        temp.put("CheckinDate","7-24-2020");
        temp.put("CheckoutDate","7-26-2020");
        temp.put("RoomType","Suite");
        temp.put("TotalPrice","120");
        list.add(temp);

        HashMap<String,String> temp2=new HashMap<String, String>();
        temp2.put("StartDate","7-24-2020");
        temp2.put("StartTime","12:00:00");
        temp2.put("HotelName","Ranger");
        temp2.put("NumberofRooms","8");
        temp2.put("CheckinDate","7-24-2020");
        temp2.put("CheckoutDate","7-26-2020");
        temp2.put("RoomType","Deluxe");
        temp2.put("TotalPrice","100");
        list.add(temp2);

        HashMap<String,String> temp3=new HashMap<String, String>();
        temp3.put("StartDate","7-24-2020");
        temp3.put("StartTime","12:00:00");
        temp3.put("HotelName","Liberty");
        temp3.put("NumberofRooms","88");
        temp3.put("CheckinDate","7-24-2020");
        temp3.put("CheckoutDate","7-26-2020");
        temp3.put("RoomType","Standard");
        temp3.put("TotalPrice","80");
        list.add(temp3);

        ListViewAdapter adapter=new ListViewAdapter(this, list);
        View header = (View)getLayoutInflater().inflate(R.layout.row_layout_list,null);
        listContent.addHeaderView(header);
        listContent.setAdapter(adapter);
        //arrayAdapter = new ArrayAdapter<HashMap<String,String>>(this, R.layout.display_reservation_layouts,R.id.StartDate ,list);
        //listContent.setAdapter(arrayAdapter);
        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String text = parent.getItemAtPosition(position).toString();

                if(activity.equals("GuestScreen"))
                {
                    Intent myIntent = new Intent(ReservationList.this, CreditCardInformation.class);
                    myIntent.putExtra("listselected",text);
                    startActivity(myIntent);
                }
                else
                {
                    Intent myIntent = new Intent(ReservationList.this, SpecificReservation.class);
                    myIntent.putExtra("listselected",text);
                    startActivity(myIntent);
                }
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
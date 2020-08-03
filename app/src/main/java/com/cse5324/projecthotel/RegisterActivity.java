package com.cse5324.projecthotel;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
/*
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
*/
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.BoringLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText userName,passWord,firstName,lastName,phone,email,address,city,state,zipcode,creditCardno,creditExpiry;
    String suserName,spassWord,sfirstName,slastName,sphone,semail,saddress,scity,sState,sZipCode,screditCardno,sCreditExpiry,sRole;
    Boolean Check = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        db = new DatabaseHelper(this);

        getSupportActionBar().setTitle("Registration"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void registerCheck(View view)
    {
        userName = (EditText) findViewById(R.id.register_username);
        passWord = (EditText) findViewById(R.id.register_password);
        firstName = (EditText) findViewById(R.id.register_firstname);
        lastName = (EditText) findViewById(R.id.register_lastname);
        phone = (EditText) findViewById(R.id.register_phone);
        email = (EditText) findViewById(R.id.register_email);
        address = (EditText) findViewById(R.id.register_address);
        city = (EditText) findViewById(R.id.register_city);
        state = (EditText) findViewById(R.id.register_state);
        zipcode = (EditText) findViewById(R.id.register_zipcode);
        creditCardno = (EditText) findViewById(R.id.register_credit_card);
        creditExpiry = (EditText) findViewById(R.id.register_cc_expiry);

        Check = true;   //Important to add here, so registration works if user edits back to correct format
        suserName = userName.getText().toString().trim();

        if (suserName.matches(""))
        {
            userName.setError("Username field cannot be empty");
            Check = false;
        }

        spassWord = passWord.getText().toString().trim();

        if (spassWord.matches(""))
        {
            passWord.setError("Password field cannot be empty");
            Check = false;
        }


        sfirstName = firstName.getText().toString().trim();

        if (sfirstName.matches(""))
        {
            firstName.setError("FirstName field cannot be empty");
            Check = false;
        }


        slastName = lastName.getText().toString().trim();

        if (slastName.matches(""))
        {
            lastName.setError("LastName field cannot be empty");
            Check = false;
        }


        sphone = phone.getText().toString().trim();

        if (sphone.matches(""))
        {
            phone.setError("PhoneNumber field cannot be empty");
            Check = false;
        }


        semail = email.getText().toString().trim();

        if (semail.matches(""))
        {
            email.setError("Email field cannot be empty");
            Check = false;
        }


        saddress = address.getText().toString().trim();

        if (saddress.matches(""))
        {
            address.setError("Address field cannot be empty");
            Check = false;
        }


        scity = city.getText().toString().trim();

        if (scity.matches(""))
        {
            city.setError("City field cannot be empty");
            Check = false;
        }


        sState = state.getText().toString().trim();

        if (sState.matches(""))
        {
            state.setError("State field cannot be empty");
            Check = false;
        }


        sZipCode = zipcode.getText().toString().trim();

        if (sZipCode.matches(""))
        {
            zipcode.setError("Zipcode field cannot be empty");
            Check = false;
        }


        screditCardno = creditCardno.getText().toString().trim();

        if (screditCardno.matches(""))
        {
            creditCardno.setError("Credit Card Number cannot be empty");
            Check = false;
        }


        sCreditExpiry = creditExpiry.getText().toString();
        String p = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

        if (!Pattern.matches(p, sCreditExpiry))
        {
            creditExpiry.setError("Use the Valid date format given");
            Check = false;
        }

            /////////////////////
        String COL_1 = "USERNAME";
        String COL_2 = "PASSWORD";
        String COL_3 = "FIRSTNAME";
        String COL_4 = "LASTNAME";
        String COL_5 = "PHONE";
        String COL_6 = "EMAIL";
        String COL_7 = "ADDRESS";
        String COL_8 = "CITY";
        String COL_9 = "STATE";
        String COL_10 = "ZIPCODE";
        String COL_11 = "CREDITCARDNO";
        String COL_12 = "CREDITCARDEXPIRY";
        String COL_13 = "ROLE";

        ContentValues cv = new ContentValues();
        cv.put(COL_1, suserName);
        cv.put(COL_2, spassWord);
        cv.put(COL_3, sfirstName);
        cv.put(COL_4, slastName);
        cv.put(COL_5, sphone);
        cv.put(COL_6, semail);
        cv.put(COL_7, saddress);
        cv.put(COL_8, scity);
        cv.put(COL_9, sState);
        cv.put(COL_10, sZipCode);
        cv.put(COL_11, screditCardno);
        cv.put(COL_12, sCreditExpiry);
        cv.put(COL_13, "g");
        boolean res = db.insertData(cv);
        if(res == true)
        {
            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainAppScreenActivity.class));
        }
        else
        {
            Toast.makeText(RegisterActivity.this, "Error Occurred in Insertion", Toast.LENGTH_SHORT).show();
        }
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
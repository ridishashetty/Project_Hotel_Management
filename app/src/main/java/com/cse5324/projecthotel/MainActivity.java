package com.cse5324.projecthotel;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText userName,passWord;
    DatabaseHelper db;
    String sUsername,sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
    }

    public void loginCheck(View view) {

        userName = (EditText) findViewById(R.id.login_username);
        sUsername = userName.getText().toString().trim();

        passWord = (EditText) findViewById(R.id.login_password);
        sPassword = passWord.getText().toString().trim();

        if(TextUtils.isEmpty(sUsername) && TextUtils.isEmpty(sPassword))
        {
            Toast.makeText(this, "Both username and password must be entered", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(sUsername))
        {
            Toast.makeText(this, "Username must be entered", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(sPassword))
        {
            Toast.makeText(this, "Password must be entered", Toast.LENGTH_SHORT).show();
        }
        else
        {
            db = new DatabaseHelper(this);

            Boolean res =  db.ValidateUser(sUsername,sPassword);

            if(res == true)
            {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, DashBoardActivity.class));
            }
            else
            {
                Toast.makeText(this, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void registerDetails(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
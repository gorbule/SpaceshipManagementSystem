package com.example.spaceshipmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit1 = findViewById(R.id.username);
        edit2 = findViewById(R.id.password);

        btn1 = findViewById(R.id.btn1);

        //Listener for button 1 - LOGIN
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login
                login();
            }
        });
    }

    public void login() {
        String username = edit1.getText().toString();
        String password = edit2.getText().toString();

        //Validation of the username and password
            //username and password - not empty
        if (username.equals("")||password.equals("")) {
            Toast.makeText(this, "Username or password is empty", Toast.LENGTH_LONG).show();
        } else if (username.equals("Alexandra")&&password.equals("0000")) {
            Toast.makeText(this, "Login was sucesful", Toast.LENGTH_LONG).show();
            Intent welcome = new Intent(LoginActivity.this,WelcomeActivity.class);
            startActivity(welcome);
        } else {
            Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_LONG).show();
        }

    }
}
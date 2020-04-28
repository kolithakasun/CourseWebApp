package com.example.courseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHelper;
import Model.User;

public class MainActivity extends AppCompatActivity {

    private Button login , register;
    private EditText userName , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void login(){

        if(TextUtils.isEmpty(userName.getText().toString())){
            Toast.makeText(MainActivity.this,"Please Enter Username!",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password.getText().toString())){
            Toast.makeText(MainActivity.this,"Please Enter Password!",Toast.LENGTH_SHORT).show();
        }
        if(!TextUtils.isEmpty(userName.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())){

            DBHelper dbHelper = new DBHelper(MainActivity.this);
            dbHelper.signIn(new User(userName.getText().toString().trim(),password.getText().toString().trim(),null));
        }
    }
}

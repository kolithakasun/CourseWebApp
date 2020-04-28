package com.example.courseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Message extends AppCompatActivity {

    private TextView subject;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        subject = findViewById(R.id.caption);
        message = findViewById(R.id.message);

        if(getIntent().getSerializableExtra("Message") != null){

            Model.Message Msg = (Model.Message) getIntent().getSerializableExtra("Message");

            subject.setText(Msg.getSubject());
            message.setText(Msg.getMessage());

        }

    }
}

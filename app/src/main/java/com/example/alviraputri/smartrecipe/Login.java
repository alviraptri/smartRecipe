package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    Button login, regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regist = (Button) findViewById(R.id.register);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), Register.class);
                startActivity(a);
                finish();
            }
        });

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent b = new Intent(getApplicationContext(), Home.class);
                Intent b = new Intent(getApplicationContext(), Quest1.class);
                startActivity(b);
                finish();
            }
        });
    }
}

package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Details extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

    }

    public void stepbystep(View view){
        Intent b = new Intent(this, StepByStep.class);
        startActivity(b);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_in_up);
    }
}

package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Quest2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest2);

    }
    public void quest2of1(View view){
        Intent a = new Intent(this, Quest3.class);
        startActivity(a);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void quest2of2(View view){
        Intent b = new Intent(this, Quest3.class);
        startActivity(b);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void quest2of3(View view){
        Intent c = new Intent(this, Quest3.class);
        startActivity(c);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}

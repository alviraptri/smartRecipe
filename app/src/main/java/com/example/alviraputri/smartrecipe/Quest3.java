package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Quest3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest3);
    }
    public void quest3of1(View view){
        Intent a = new Intent(this, Quest4.class);
        startActivity(a);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void quest3of2(View view){
        Intent b = new Intent(this, Quest4.class);
        startActivity(b);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}

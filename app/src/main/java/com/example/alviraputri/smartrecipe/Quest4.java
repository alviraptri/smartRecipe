package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Quest4 extends AppCompatActivity {
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest4);
    }

    public void quest4of1(View view){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int id_user = pref.getInt("id", 0);
        url = "http://10.0.2.2/smartrecipe/input.php?id_user=" + id_user + "&id_question=4&answer=1";
        Intent a = new Intent(this, Quest5.class);
        startActivity(a);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void quest4of2(View view){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int id_user = pref.getInt("id", 0);
        url = "http://10.0.2.2/smartrecipe/input.php?id_user=" + id_user + "&id_question=4&answer=2";
        Intent b = new Intent(this, Quest5.class);
        startActivity(b);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}

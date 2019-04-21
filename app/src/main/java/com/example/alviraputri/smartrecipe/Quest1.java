package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Quest1 extends AppCompatActivity {
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest1);
    }

    public void quest1(View view){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int id_user = pref.getInt("id", 0);
        url = "http://10.0.2.2/smartrecipe/input.php?id_user=" + id_user + "&id_question=1&answer=1";
        Intent a = new Intent(this, Quest2.class);
        startActivity(a);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void quest2(View view){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int id_user = pref.getInt("id", 0);
        url = "http://10.0.2.2/smartrecipe/input.php?id_user=" + id_user + "&id_question=1&answer=2";
        Intent b = new Intent(this, Quest2.class);
        startActivity(b);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void quest3(View view){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int id_user = pref.getInt("id", 0);
        url = "http://10.0.2.2/smartrecipe/input.php?id_user=" + id_user + "&id_question=1&answer=3";
        Intent c = new Intent(this, Quest2.class);
        startActivity(c);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}

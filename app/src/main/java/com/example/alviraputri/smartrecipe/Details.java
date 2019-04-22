package com.example.alviraputri.smartrecipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Details extends AppCompatActivity {
    TextView title, user, lvl, ingre;
    Button sbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        sbs = (Button) findViewById(R.id.sbs);
        title = (TextView) findViewById(R.id.textView16);
        user = (TextView) findViewById(R.id.textView22);
        lvl = (TextView) findViewById(R.id.textView18);
        ingre = (TextView) findViewById(R.id.ingg);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        int id_rec = pref.getInt("id_rec", 0);
        String us = pref.getString("orang", "");
        String jdl = pref.getString("title_recipe", "");
        String lev = pref.getString("lvel", "");
        String ing = pref.getString("ingre", "");

        title.setText(jdl);
        user.setText(us);
        lvl.setText(lev);
        ingre.setText(ing);
    }

    public void stepbystep(View view){
        Intent b = new Intent(this, StepByStep.class);
        startActivity(b);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_in_up);
    }
}

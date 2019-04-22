package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Quest1 extends AppCompatActivity {
    String url = "http://sistechuph.com/smartrecipe/input.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest1);
    }

    public void quest1(View view){
        getData(1, 1);
        Intent a = new Intent(this, Quest2.class);
        startActivity(a);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void quest2(View view){
        getData(1, 2);
        Intent b = new Intent(this, Quest2.class);
        startActivity(b);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void quest3(View view){
        getData(1, 3);
        Intent c = new Intent(this, Quest2.class);
        startActivity(c);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void getData(final int a, final int b) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("ERRORRRR", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                int id_user = pref.getInt("id", 0);
                Log.e("ID USER", ""+id_user);
                Map<String,String> params = new HashMap<>();
                params.put("id_user", String.valueOf(id_user));
                params.put("id_question", String.valueOf(a));
                params.put("answer", String.valueOf(b));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

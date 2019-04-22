package com.example.alviraputri.smartrecipe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button login, regist;
    EditText email, pass;
    String url = "http://sistechuph.com/smartrecipe/login.php";
    String url2 = "http://sistechuph.com/smartrecipe/quest.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.fullname);
        pass = (EditText) findViewById(R.id.password);

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
                getData();
            }
        });
    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("WOI", response);
                    JSONObject object = new JSONObject(response);
                    for(int i = 0; i < object.length(); i++) {
                        try {
                            JSONObject jsonObject = object.getJSONObject("user");
                            int id = jsonObject.getInt("id");
                            String nama = jsonObject.getString("fullname");
                            String email = jsonObject.getString("email");
                            int stt = jsonObject.getInt("status");

                            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();

                            editor.clear();
                            editor.commit();
                            editor.putInt("id", id);
                            editor.putString("nama", nama);
                            editor.putString("email", email);
                            editor.apply();

                            if(stt == 1) {
                                Intent q = new Intent(getApplicationContext(), Quest1.class);
                                startActivity(q);
                            }
                            else {
                                getQuest();
                                Intent w = new Intent(getApplicationContext(), BottomNav.class);
                                startActivity(w);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", email.getText().toString());
                params.put("password", pass.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getQuest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                try {
                    Log.e("WOI", response);
                    JSONObject object = new JSONObject(response);
                    JSONArray arr = object.getJSONArray("quest");
                    int[] q = new int[100];
                    int[] an = new int[100];
                    for(int i = 0; i < arr.length(); i++) {
                        try {
                            JSONObject jsonObject = arr.getJSONObject(i);
                            q[i] = jsonObject.getInt("question");
                            an[i] = Integer.parseInt(jsonObject.getString("answer"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    int meat = 0;
                    int veg = 0;
                    int fruit = 0;
                    int sea = 0;

                    for(int j = 0; j < arr.length(); j++) {
                        if(j == 0) {
                            if(an[j] == 1) {
                                meat++;
                            }
                            if(an[j] == 2) {
                                veg++;
                                fruit++;
                            }
                            if(an[j] == 3) {
                                meat++;
                                veg++;
                            }
                        }
                        if(j == 1) {
                            if(an[j] == 1) {
                                meat++;
                            }
                            if(an[j] == 2) {
                                veg++;
                                fruit++;
                            }
                            if(an[j] == 3) {
                                sea++;
                            }
                        }
                        if(j == 2) {
                            if(an[j] == 1) {
                                veg++;
                                fruit++;
                            }
                            if(an[j] == 2) {
                                meat++;
                                sea++;
                            }
                        }
                        if(j == 3) {
                            if(an[j] == 1) {
                                meat++;
                            }
                            if(an[j] == 2) {
                                sea++;
                            }
                        }
                        if(j == 4) {
                            if(an[j] == 1) {
                                sea--;
                            }
                            if(an[j] == 2) {
                                veg--;
                            }
                        }
                    }
                    if(meat > veg && meat > fruit && meat > sea) {
                        editor.putInt("counter", 1);
                        Toast.makeText(getApplicationContext(), ""+1, Toast.LENGTH_LONG).show();
                        editor.apply();
                    }
                    if(veg > meat && veg > fruit && veg > sea) {
                        editor.putInt("counter", 2);
                        Toast.makeText(getApplicationContext(), ""+2, Toast.LENGTH_LONG).show();
                        editor.apply();
                    }
                    if(fruit > meat && fruit > veg && fruit > sea) {
                        editor.putInt("counter", 3);
                        Toast.makeText(getApplicationContext(), ""+3, Toast.LENGTH_LONG).show();
                        editor.apply();
                    }
                    if(sea > meat && sea > fruit && sea > veg) {
                        editor.putInt("counter", 4);
                        Toast.makeText(getApplicationContext(), ""+4, Toast.LENGTH_LONG).show();
                        editor.apply();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", email.getText().toString());
                params.put("password", pass.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
package com.example.alviraputri.smartrecipe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class addRecipe extends AppCompatActivity {

    Button next, back, foto;
    EditText title, ing;
    Spinner lvl, cat;
    String url1 = "http://sistechuph.com/smartrecipe/level.php";
    String url2 = "http://sistechuph.com/smartrecipe/category.php";
    String catt, leve;
    private List<Spinnerlist> lev, cate;
    private ArrayAdapter<Spinnerlist> adapter, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        next = (Button) findViewById(R.id.next);
        back = (Button) findViewById(R.id.back);
        foto = (Button) findViewById(R.id.foto);

        title = (EditText) findViewById(R.id.editText2);
        ing = (EditText) findViewById(R.id.editText3);

        lvl = findViewById(R.id.spinner1);
        lev = new ArrayList<>();
        getLevel();
        adapter = new ArrayAdapter<Spinnerlist>(this, android.R.layout.simple_spinner_item, lev);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        lvl.setAdapter(adapter);

        cat = findViewById(R.id.category);
        cate = new ArrayList<>();
        getCategory();
        adapter2 = new ArrayAdapter<Spinnerlist>(this, android.R.layout.simple_spinner_item, cate);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        cat.setAdapter(adapter2);

        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catt = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                leve = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("nama_category", catt);
                editor.putString("nama_level", leve);
                editor.putString("ingre", ing.getText().toString());
                editor.putString("title", title.getText().toString());
                editor.apply();

                Intent a = new Intent(getApplicationContext(), addRecipe2.class);
                startActivity(a);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(addRecipe.this, fragmentDiscover.class);
                startActivity(a);
                finish();
            }
        });
    }

    public void getLevel() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("WOI", response);
                    JSONObject object = new JSONObject(response);
                    for(int i = 0; i < object.length(); i++) {
                        try {
                            JSONObject jsonObject = object.getJSONObject("level");
                            int id = jsonObject.getInt("id_level");
                            String nama = jsonObject.getString("nama_level");

                            Spinnerlist spinner = new Spinnerlist(nama, id);
                            spinner.setSname(nama);
                            spinner.setSval(id);
                            lev.add(spinner);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void getCategory() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("WOI", response);
                    JSONObject object = new JSONObject(response);
                    for(int i = 0; i < object.length(); i++) {
                        try {
                            JSONObject jsonObject = object.getJSONObject("category");
                            int id = jsonObject.getInt("id_category");
                            String nama = jsonObject.getString("nama_category");

                            Spinnerlist spinnerlist = new Spinnerlist(nama, id);
                            spinnerlist.setSname(nama);
                            spinnerlist.setSval(id);
                            cate.add(spinnerlist);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter2.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
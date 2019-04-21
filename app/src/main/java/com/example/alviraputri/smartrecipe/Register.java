package com.example.alviraputri.smartrecipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    Button login, regist;
    EditText nama, email, pw;
    String url = "http://sistechuph.com/smartrecipe/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nama = (EditText) findViewById(R.id.fullname);
        email = (EditText) findViewById(R.id.email);
        pw = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);
        regist = (Button) findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), Login.class);
                startActivity(a);
                finish();
            }
        });

        /*regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    public void buregis(View view) {
        //url = "http://sistechuph.com/smartrecipe/register.php?name=" + nama.getText().toString() + "&password=" + pw.getText().toString() + "&email="+email.getText().toString();
        getData();
        //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
        Log.v("XXXXXXXXXXXXXXXXXXXXXX", url);
    }

    private void getData() {
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
                Map<String,String> params = new HashMap<>();
                params.put("name", nama.getText().toString());
                params.put("password", pw.getText().toString());
                params.put("email", email.getText().toString());
                //params.put("nickname",data[3]);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

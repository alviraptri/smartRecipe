package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {
    Button login, regist;
    EditText email, pass;

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
                String url = "http://10.0.2.2/smartrecipe/login.php?email=" + email.getText().toString() + "&password=" + pass.getText().toString();
                new getMySqlData().execute(url);
            }
        });
    }

    public class getMySqlData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            //before works
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                String NewsData;
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(7000);

                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    NewsData = ConvertInputToStringNoChange(in);
                    publishProgress(NewsData);
                } finally {
                    urlConnection.disconnect();
                }

            } catch (Exception ex) {
            }
            return null;
        }


        protected void onProgressUpdate(String... progress) {

            try {
                JSONObject json = new JSONObject(progress[0]);

                int id = json.getInt("id");
                String nama = json.getString("fullname");
                String em = json.getString("email");
                int stats = json.getInt("status");

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                editor.putInt("id", id);
                editor.putString("name", nama);
                editor.putString("email", em);
                editor.apply();
                if(stats == 1) {
                    Intent i = new Intent(getApplicationContext(), Quest1.class);
                    startActivity(i);
                    finish();
                }
                else if(stats == 2) {
                    Intent i = new Intent(getApplicationContext(), BottomNav.class);
                    startActivity(i);
                    finish();
                }

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "GAGAL MEMUAT DATA API", Toast.LENGTH_LONG).show();
            }
        }
    }




    public static String ConvertInputToStringNoChange(InputStream inputStream) {

        BufferedReader bureader=new BufferedReader( new InputStreamReader(inputStream));
        String line ;
        String linereultcal="";

        try{
            while((line=bureader.readLine())!=null) {
                linereultcal+=line;

            }
            inputStream.close();


        }catch (Exception ex){}

        return linereultcal;
    }
}

package com.example.alviraputri.smartrecipe;

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

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Register extends AppCompatActivity {
    Button login, regist;
    EditText nama, email, pw;

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

    public void buRegis(View view) {
        String url = "http://10.0.2.2/smartrecipe/register.php?name=" + nama.getText().toString() + "&password=" + pw.getText().toString() + "&email="+email.getText().toString();
        new getMySqlData().execute(url);
        Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
        Log.v("XXXXXXXXXXXXXXXXXXXXXX", url);
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

                String msg = json.getString("msg");
                Toast.makeText(getApplicationContext(), progress[0], Toast.LENGTH_LONG).show();
                Intent a = new Intent(getApplicationContext(), Login.class);
                startActivity(a);
                finish();
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

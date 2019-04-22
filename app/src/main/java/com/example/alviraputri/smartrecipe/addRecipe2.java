package com.example.alviraputri.smartrecipe;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class addRecipe2 extends AppCompatActivity {
    Button back, submit, pic;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmapsimg;
    private ImageView nimage;
    private String url = "http://sistechuph.com/smartrecipe/upload.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe2);

        back = (Button) findViewById(R.id.back);
        submit = (Button) findViewById(R.id.next);
        pic = (Button) findViewById(R.id.foto);
        nimage = (ImageView)  findViewById(R.id.imageView12);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), addRecipe.class);
                startActivity(a);
                finish();
            }
        });

        /*pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });*/

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage();
            }
        });
    }

    public void ima(View v) {
        selectimage();
    }

    public void selectimage(){
        Log.e("TEST IMAGE", "MASUK SELECT IMAGE");
        Intent imgintent = new Intent();
        imgintent.setType("image/*");
        imgintent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(imgintent,IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode  == IMG_REQUEST && resultCode==RESULT_OK &&  data != null){
            Uri path = data.getData();

            try {
                bitmapsimg = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                nimage.setImageBitmap(bitmapsimg);
                nimage.setVisibility(View.VISIBLE);


            } catch (IOException e){

            }
        }

    }

    private void uploadimage(){
        Log.e("TIS", "MASUK");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject  = new JSONObject(response);

                            String Response = jsonObject.getString("response");
                            Toast.makeText(getApplicationContext(), Response,Toast.LENGTH_LONG).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } )
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                Log.e("GAMBAR", imageToString(bitmapsimg));
                params.put("image",imageToString(bitmapsimg));
                return params;
            }
        };

        MySingleton.getmInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgbytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgbytes, Base64.DEFAULT);

    }
}
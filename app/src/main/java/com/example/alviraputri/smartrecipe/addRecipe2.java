package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class addRecipe2 extends AppCompatActivity {
    Button back, submit, pic;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmapsimg;
    private ImageView nimage;
    private String url = "http://sistechuph.com/smartrecipe/upload.php";
    EditText step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe2);

        back = (Button) findViewById(R.id.back);
        submit = (Button) findViewById(R.id.next2);
        pic = (Button) findViewById(R.id.foto);
        nimage = (ImageView)  findViewById(R.id.imageView12);
        step = (EditText) findViewById(R.id.textView4);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), addRecipe.class);
                startActivity(a);
                finish();
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });

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
                        Intent i = new Intent(getApplicationContext(), BottomNav.class);
                        startActivity(i);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } )
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                String nama_cat = pref.getString("nama_category", "");
                String nama_lvl = pref.getString("nama_level", "");
                String ing = pref.getString("ingre", "");
                String title = pref.getString("title", "");
                int id_user = pref.getInt("id", 0);

                Map<String,String> params = new HashMap<>();
                params.put("title", title);
                params.put("id_user", String.valueOf(id_user));
                params.put("cate", nama_cat);
                params.put("level", nama_lvl);
                params.put("ing", ing);
                params.put("step", step.getText().toString());
                params.put("image",imageToString(bitmapsimg));
                return params;
            }
        };

        //MySingleton.getmInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgbytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgbytes, Base64.DEFAULT);

    }
}
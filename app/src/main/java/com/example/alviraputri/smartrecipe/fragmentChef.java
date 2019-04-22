package com.example.alviraputri.smartrecipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;

public class fragmentChef extends Fragment {
    TextView welcome;
    String url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences pref = this.getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String name = pref.getString("nama", "");
        url = "http://sistechuph.com/smartrecipe/";

        //getData();

        View rootView = inflater.inflate(R.layout.fragment_chef, container, false);
        welcome = (TextView) rootView.findViewById(R.id.textView11);

        welcome.setText("Welcome, "+name);
        Button m = (Button) rootView.findViewById(R.id.tambah);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getActivity(), addRecipe.class);
                startActivity(m);
            }
        });

        return rootView;
    }

    /*private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        //JSONArray nama = jsonObject.getJSONArray("nama_produk");
                        //JSONArray harga = jsonObject.getJSONArray("harga");
                        String nama = jsonObject.getString("nama_produk");
                        String harga = jsonObject.getString("harga");
                        //Log.v("TESTTTT", nama);
                        //Toast.makeText(getApplicationContext(), nama, Toast.LENGTH_LONG).show();
                        Product product = new Product(nama, harga);
                        product.setName(nama);
                        product.setPrice("Rp " + harga);

                        productList.add(product);
                        productList2 = Arrays.asList(product);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                productadapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }*/
}

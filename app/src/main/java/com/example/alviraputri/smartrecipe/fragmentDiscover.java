package com.example.alviraputri.smartrecipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class fragmentDiscover extends Fragment {

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView myrecyclerview;
    private List<Discover> lstDiscover;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerViewAdapter.OnNoteListener notes;
    String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        url = "http://sistechuph.com/smartrecipe/selectRecipe.php";
        View rootView = inflater.inflate(R.layout.fragment_discover, container, false);

        lstDiscover = new ArrayList<>();

        getData();

        myrecyclerview =(RecyclerView) rootView.findViewById(R.id.discover_recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstDiscover, notes);
        linearLayoutManager = new LinearLayoutManager(this.getActivity());
        dividerItemDecoration = new DividerItemDecoration(myrecyclerview.getContext(), linearLayoutManager.getOrientation());

        myrecyclerview.setHasFixedSize(true);
        myrecyclerview.setLayoutManager(linearLayoutManager);
        myrecyclerview.addItemDecoration(dividerItemDecoration);
        myrecyclerview.setAdapter(recyclerViewAdapter);

        /*Button m = (Button) rootView.findViewById(R.id.menu1);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getActivity(), Details.class);
                startActivity(m);
            }
        });*/

        Button s = (Button) rootView.findViewById(R.id.search);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearch();
            }
        });
        return rootView;
    }

    public void openSearch(){
        Intent s = new Intent(getActivity(), search.class);
        startActivity(s);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id_recipe");
                        String nama = jsonObject.getString("recipe_name");
                        String nama_user = jsonObject.getString("user_name");
                        String level = jsonObject.getString("lvl");
                        String ing = jsonObject.getString("ing");
                        String ins = jsonObject.getString("ins");
                        int catt = jsonObject.getInt("category");
                        Discover discover = new Discover(id, nama, nama_user, level, ing, ins, catt);
                        discover.setJudul(nama);
                        discover.setLevel(level);
                        discover.setUser(nama_user);
                        discover.setID(id);
                        discover.setIng(ing);
                        discover.setIns(ins);
                        discover.setCate(catt);
                        Log.e("XXXXXXXXXXXXXXXXXXXXXXXXXXXX", ""+catt);

                        lstDiscover.add(discover);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                recyclerViewAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(jsonArrayRequest);
    }
}
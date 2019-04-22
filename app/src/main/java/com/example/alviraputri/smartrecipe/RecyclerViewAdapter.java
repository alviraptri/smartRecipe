package com.example.alviraputri.smartrecipe;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    List<Discover> mData;
    private OnNoteListener notes;
    //Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Discover> mData, RecyclerViewAdapter.OnNoteListener notes){
        this.mContext = mContext;
        this.mData = mData;
        this.notes = notes;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_discover,viewGroup,false);
        //final MyViewHolder vHolder = new MyViewHolder(v);
        return new MyViewHolder(v, notes);

        //Dialog ini
        /*myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.details);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView detail_judul_tv = (TextView) myDialog.findViewById(R.id.detail_judul_id);
                TextView detail_user_tv = (TextView) myDialog.findViewById(R.id.detail_user_id);
                detail_judul_tv.setText(mData.get(vHolder.getAdapterPosition()).getJudul());
                detail_user_tv.setText(mData.get(vHolder.getAdapterPosition()).getUser());
                myDialog.show();
            }
        });*/
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder myViewHolder, int i) {
        Discover discover = mData.get(i);
        SharedPreferences pref = mContext.getSharedPreferences("MyPref", MODE_PRIVATE);
        int counter = pref.getInt("counter", 0);
        if(counter == discover.getCate()) {
            ((MyViewHolder)myViewHolder).tv_judul.setText("Recommended = "+discover.getJudul());
        }
        else {
            ((MyViewHolder)myViewHolder).tv_judul.setText(discover.getJudul());
        }

        ((MyViewHolder)myViewHolder).tv_user.setText("By "+discover.getUser());
        ((MyViewHolder)myViewHolder).tv_like.setText("Level : "+discover.getLevel());

        /*myViewHolder.tv_judul.setText(mData.get(i).getJudul());
        myViewHolder.tv_user.setText(mData.get(i).getUser());
        myViewHolder.tv_like.setText(mData.get(i).getLevel());*/

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //private LinearLayout item_discover;
        private TextView tv_judul, tv_user, tv_like;
        OnNoteListener note;

        public MyViewHolder(View itemView, OnNoteListener notes){
            super(itemView);
            //item_discover = (LinearLayout) itemView.findViewById(R.id.item_discover_id);
            tv_judul = (TextView) itemView.findViewById(R.id.judul_discover);
            tv_user = (TextView) itemView.findViewById(R.id.user_discover);
            tv_like = (TextView) itemView.findViewById(R.id.like_discover);
            this.note = notes;
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            Discover discover = mData.get(getAdapterPosition());
            int id = discover.getID();
            String judul = discover.getJudul();
            String orang = discover.getUser();
            String lev = discover.getLevel();
            String ingre = discover.getIng();
            String inst = discover.getIns();
            SharedPreferences pref = mContext.getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("id_rec", id);
            editor.putString("title_recipe", judul);
            editor.putString("orang", orang);
            editor.putString("lvel", lev);
            editor.putString("ingre", ingre);
            editor.putString("inst", inst);
            editor.apply();
            Intent i = new Intent(mContext, Details.class);
            mContext.startActivity(i);
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
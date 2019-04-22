package com.example.alviraputri.smartrecipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpinnerDropdown extends ArrayAdapter<Spinnerlist> {

    private Context context;
    private List<Spinnerlist> spinnerr;

    public SpinnerDropdown(@NonNull Context context, ArrayList<Spinnerlist> spinnerr) {
        super(context, 0, spinnerr);
        this.context = context;
        this.spinnerr = spinnerr;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.activity_add_recipe, parent, false);
        TextView names = (TextView) view.findViewById(R.id.spinnername);
        names.setText(spinnerr.get(position).getSname());
        return view;
    }

}

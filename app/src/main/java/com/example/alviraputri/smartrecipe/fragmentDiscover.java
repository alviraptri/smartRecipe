package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class fragmentDiscover extends Fragment {

    /*ListView list_view_tasks;
    tasklistAdapter taskAdapter;*/


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_discover, container, false);

        //list_view_tasks = getView().findViewById(R.id.listView);

        Button m = (Button) rootView.findViewById(R.id.menu1);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getActivity(), Details.class);
                startActivity(m);
            }
        });

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
}

package com.example.alviraputri.smartrecipe;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class BottomNav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        BottomNavigationView bottomNav = findViewById(R.id.botNav);

        fragmentDiscover fdis = new fragmentDiscover();
        FragmentTransaction fragmendis = getSupportFragmentManager().beginTransaction();
        fragmendis.replace(R.id.fragmentContainer, fdis, "Fragment Name");
        fragmendis.commit();

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_discover:
                        fragmentDiscover fdis = new fragmentDiscover();
                        FragmentTransaction fragmendis = getSupportFragmentManager().beginTransaction();
                        fragmendis.replace(R.id.fragmentContainer, fdis, "Fragment Name");
                        fragmendis.commit();
                        break;

                    case R.id.nav_chef:
                        fragmentChef fchef = new fragmentChef();
                        FragmentTransaction fragmenchef = getSupportFragmentManager().beginTransaction();
                        fragmenchef.replace(R.id.fragmentContainer, fchef, "Fragment Name");
                        fragmenchef.commit();
                        break;
                }
                return true;
            }
        });
    }
}

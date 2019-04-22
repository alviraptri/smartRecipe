package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StepByStep extends AppCompatActivity {
    Button back;
    TextView ins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_by_step);

        back = (Button) findViewById(R.id.back);
        ins = (TextView) findViewById(R.id.ins);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String instruction = pref.getString("inst","");
        ins.setText(instruction);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(StepByStep.this, fragmentDiscover.class);
                startActivity(a);
                finish();
            }
        });
    }

    public void slideDown(View view){
        Intent b = new Intent(this, Details.class);
        startActivity(b);
        overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down);
    }
}

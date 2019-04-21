package com.example.alviraputri.smartrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StepByStep extends AppCompatActivity {
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_by_step);

        back = (Button) findViewById(R.id.back);

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

package com.example.app.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.app.R;

public class LienheActivity extends AppCompatActivity {

    Toolbar toolbarlh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lienhe);
        toolbarlh = findViewById(R.id.toolbarlienhe);
        setSupportActionBar(toolbarlh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

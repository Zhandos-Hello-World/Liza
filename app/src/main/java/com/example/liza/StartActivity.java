package com.example.liza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button start = findViewById(R.id.start);
        start.setOnClickListener(i -> {
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
        });
    }
}

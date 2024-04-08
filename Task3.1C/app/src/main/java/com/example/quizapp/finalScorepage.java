package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class finalScorepage extends AppCompatActivity {
    private TextView dUsername;
    private TextView dscore;
    private Button newquizbutton;
    private Button finishbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_scorepage);
        Intent intent = getIntent();
        if (intent != null) {
            String dusername = intent.getStringExtra("username");
            int dfinalscore = intent.getIntExtra("finalscore", 0);
            dUsername = findViewById(R.id.apusername);
            dscore = findViewById(R.id.score);
            dUsername.setText("Congratulations, " + dusername);
            dscore.setText(dfinalscore + "/ 10");
        }
        newquizbutton = findViewById(R.id.newquizbutton);
        finishbutton = findViewById(R.id.finishbutton);
        newquizbutton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(finalScorepage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
                                         });
        finishbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishAffinity();
                    }
                });
    }
}
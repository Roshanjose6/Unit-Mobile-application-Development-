package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quizstartpage extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button buttonSubmit;
    private RadioGroup radioGroup;
    private RadioButton radioButtonCorrect;

    private TextView displayUsername;
    private TextView displayquestion;
    private String[] questions = {
            "What is the capital of Australia?",
            "Which animal is native to Australia and is known for carrying its young in a pouch?",
            "What is the nickname commonly used to refer to Australia?",
            "What is the famous Australian spread made from yeast extract?",
            "What is the largest city in Australia by population?"
    };

    private String[][] options = {
            {"Canberra", "Sydney", "Brisbane"},
            {"Elephant", "Kangaroo", "Polar Bear"},
            { "The Maple Leaf Country", "The Land Down Under", "The City of Sails"},
            {"Vegemite","Nutella","Peanut Butter"},
            {"Sydney","Melbourne","Brisbane"}
    };

    private String[] correctAnswers = {
            "Canberra", "Kangaroo", "The Land Down Under", "Vegemite","Sydney"
    };
    private int totalScore = 0;
    private int currentQuestionIndex = 0;
    private Handler handler = new Handler();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizstartpage);

        progressBar = findViewById(R.id.progressBar);
        buttonSubmit = findViewById(R.id.submitanswer);
        radioGroup = findViewById(R.id.radiogroup);
        displayUsername = findViewById(R.id.name);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("UserName");
        displayUsername.setText(userName);
        displayquestion = findViewById(R.id.dquestion);
        loadQuestion();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected RadioButton ID
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                // Check if the selected answer is correct

                if (selectedRadioButton != null && selectedRadioButton.getText().toString().equals(correctAnswers[currentQuestionIndex])) {
                    selectedRadioButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    totalScore += 2;
                } else {
                    selectedRadioButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
//                        totalScore -= 2;
                }
                // Delay for 1 second before moving to the next question
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentQuestionIndex++;
                        if (currentQuestionIndex < questions.length) {
                            loadQuestion();
                        } else {
                            Intent intent = new Intent(Quizstartpage.this, finalScorepage.class);
                            intent.putExtra("username", userName); // Assuming username is a String variable containing the username
                            intent.putExtra("finalscore", totalScore);
                            startActivity(intent);
                        }
                    }
                }, 1000);
            }
        });
    }
        private void loadQuestion() {
            displayquestion.setText(questions[currentQuestionIndex]);
            radioGroup.removeAllViews();
            for (int i = 0; i < options[currentQuestionIndex].length; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(options[currentQuestionIndex][i]);
                radioButton.setId(i); // Set unique ID for each RadioButton
                radioGroup.addView(radioButton);
            }
            // Update progress bar based on the number of questions completed
            int progress = (int)(((float) currentQuestionIndex / questions.length) * 100);
            progressBar.setProgress(progress);
        }
}
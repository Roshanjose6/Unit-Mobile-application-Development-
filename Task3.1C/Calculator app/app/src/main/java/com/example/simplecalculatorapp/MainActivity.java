package com.example.simplecalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextValue1;
    private EditText editTextValue2;
    private Button buttonAdd;
    private Button buttonSubtract;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTextValue1 = findViewById(R.id.inputvalue1);
        editTextValue2 = findViewById(R.id.inputvalue2);
        buttonAdd = findViewById(R.id.resultadd);
        buttonSubtract = findViewById(R.id.resultsub);
        textViewResult = findViewById(R.id.answer);

        // Set click listeners for buttons
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addValues();
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtractValues();
            }
        });
    }

    private void addValues() {
        if (editTextValue1.getText().toString().isEmpty() || editTextValue2.getText().toString().isEmpty()) {
            Toast.makeText(this, "please enter both values", Toast.LENGTH_SHORT).show();
            return;
        }
        // Get values from input fields
        int value1 = Integer.parseInt(editTextValue1.getText().toString());
        int value2 = Integer.parseInt(editTextValue2.getText().toString());

        // Perform addition
        int result = value1 + value2;

        // Display result
        textViewResult.setText(" " + result);
    }

    private void subtractValues() {
        if (editTextValue1.getText().toString().isEmpty() || editTextValue2.getText().toString().isEmpty()) {
            Toast.makeText(this, "please enter both values", Toast.LENGTH_SHORT).show();
            return;
        }
        // Get values from input fields
        int value1 = Integer.parseInt(editTextValue1.getText().toString());
        int value2 = Integer.parseInt(editTextValue2.getText().toString());

        // Perform subtraction
        int result = value1 - value2;

        // Display result
        textViewResult.setText(" " + result);
    }
}
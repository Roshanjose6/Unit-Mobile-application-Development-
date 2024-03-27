package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] Unitnames = {"Length", "Temperature", "Weight"};
    private Spinner mainspinner;
    private Spinner fromUnitSpinner;
    private Spinner toUnitSpinner;
    private EditText inputEditText;
    private TextView resultTextView;
    Button convertbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEditText = findViewById(R.id.inputvalue);
        resultTextView = findViewById(R.id.resultvalue);
        mainspinner = findViewById(R.id.unittype);
        convertbutton = findViewById(R.id.convert);
        fromUnitSpinner = findViewById(R.id.type1);
        toUnitSpinner = findViewById(R.id.type2);
        ArrayAdapter spinneradapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Unitnames);
        mainspinner.setAdapter(spinneradapter);
        mainspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Switchaccordinly();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        convertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConvertButtonClick();
            }
        });
    }

    private void Switchaccordinly() {
        String[] lengthUnits = {"Inch", "Yard", "Foot", "Mile"};
        String[] tempunits = {"Celsius", "Fahrenheit", "Kelvin"};
        String[] weightunits = {"Pound", "Ounce", "Ton"};
        ArrayAdapter<String> unitAdapter;

        switch (mainspinner.getSelectedItem().toString()) {
            case "Length":
                unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lengthUnits);
                break;
            case "Temperature":
                unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tempunits);
                break;
            case "Weight":
                unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weightunits);
                break;
            default:
                // Default to length units
                unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lengthUnits);
        }

        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(unitAdapter);
        toUnitSpinner.setAdapter(unitAdapter);
    }

    private double convertUnit(String fromUnit, String toUnit, double inputValue) {
        double result = 0;
        // Length Conversions
        if (fromUnit.equals("Inch") && toUnit.equals("Yard")) {
            result = inputValue / 36;
        } else if (fromUnit.equals("Inch") && toUnit.equals("Foot")) {
            result = inputValue / 12;
        } else if (fromUnit.equals("Inch") && toUnit.equals("Mile")) {
            result = inputValue / 63360;
        }
        // Yard conversions
        else if (fromUnit.equals("Yard") && toUnit.equals("Inch")) {
            result = inputValue * 36;
        } else if (fromUnit.equals("Yard") && toUnit.equals("Foot")) {
            result = inputValue * 3;
        } else if (fromUnit.equals("Yard") && toUnit.equals("Mile")) {
            result = inputValue / 1760;
        }
        // Foot conversions
        else if (fromUnit.equals("Foot") && toUnit.equals("Inch")) {
            result = inputValue * 12;
        } else if (fromUnit.equals("Foot") && toUnit.equals("Yard")) {
            result = inputValue / 3;
        } else if (fromUnit.equals("Foot") && toUnit.equals("Mile")) {
            result = inputValue / 5280;
        }
        // Mile conversions
        else if (fromUnit.equals("Mile") && toUnit.equals("Inch")) {
            result = inputValue * 63360;
        } else if (fromUnit.equals("Mile") && toUnit.equals("Yard")) {
            result = inputValue * 1760;
        } else if (fromUnit.equals("Mile") && toUnit.equals("Foot")) {
            result = inputValue * 5280;
        }
        // Weight Conversions
        else if (fromUnit.equals("Pound") && toUnit.equals("Ounce")) {
            result = inputValue * 16;
        } else if (fromUnit.equals("Pound") && toUnit.equals("Ton")) {
            result = inputValue / 2000;
        }
        // Ounce conversions
        else if (fromUnit.equals("Ounce") && toUnit.equals("Pound")) {
            result = inputValue / 16;
        } else if (fromUnit.equals("Ounce") && toUnit.equals("Ton")) {
            result = inputValue / 32000;
        }
        // Ton conversions
        else if (fromUnit.equals("Ton") && toUnit.equals("Pound")) {
            result = inputValue * 2000;
        } else if (fromUnit.equals("Ton") && toUnit.equals("Ounce")) {
            result = inputValue * 32000;
        }
        // Temperature Conversions
        else if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
            result = (inputValue * 1.8) + 32;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            result = (inputValue - 32) / 1.8;
        } else if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin")) {
            result = inputValue + 273.15;
        } else if (fromUnit.equals("Kelvin") && toUnit.equals("Celsius")) {
            result = inputValue - 273.15;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Kelvin")) {
            result = (inputValue - 32) * 5 / 9 + 273.15;
        } else if (fromUnit.equals("Kelvin") && toUnit.equals("Fahrenheit")) {
            result = inputValue * 9 / 5 - 459.67;
        } else {
            // No conversion found
            Toast.makeText(this, "Invalid conversion.", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public void onConvertButtonClick() {
        String inputStr = inputEditText.getText().toString().trim();
        if (!inputStr.isEmpty()) {
            if (!isInteger(inputStr)) {
                Toast.makeText(this, "Please enter a valid integer value.", Toast.LENGTH_SHORT).show();
            } else {
                double inputValue = Double.parseDouble(inputStr);
                String fromUnit = fromUnitSpinner.getSelectedItem().toString();
                String toUnit = toUnitSpinner.getSelectedItem().toString();

                if (fromUnit.equals(toUnit)) {
                    Toast.makeText(this, "Source and destination units are the same.", Toast.LENGTH_SHORT).show();
                } else {
                    double result = convertUnit(fromUnit, toUnit, inputValue);
                    resultTextView.setText(String.valueOf(result));
                }
            }
        } else {
            Toast.makeText(this, "Enter a value.", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
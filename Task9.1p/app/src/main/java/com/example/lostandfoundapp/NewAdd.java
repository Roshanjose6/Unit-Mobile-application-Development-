package com.example.lostandfoundapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class NewAdd extends AppCompatActivity {
    EditText name,phone,location,date,desc;
    Button buttonsave;
    RadioGroup radioGroup;
    DataBaseHelper databasehelper;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private Location curentloc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add);
        buttonsave=findViewById(R.id.save);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        desc=findViewById(R.id.desc);
        date=findViewById(R.id.lf_date);
        location=findViewById(R.id.location);
        radioGroup = findViewById(R.id.radio_group);
        databasehelper= new DataBaseHelper(this);
        date.setInputType(InputType.TYPE_NULL);
        date.setFocusable(false);
        RadioButton radioButton1 = findViewById(R.id.radioButtonlost);
        RadioButton radioButton2 = findViewById(R.id.radioButtonfound);
        @SuppressLint("ResourceType") ColorStateList colorStateList = AppCompatResources.getColorStateList(this, R.drawable.radiobuttonselectioncolor);

        if (colorStateList != null) {
            radioButton1.setButtonTintList(colorStateList);
            radioButton2.setButtonTintList(colorStateList);
        }
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyCOQKUR6NxtJJmla0PKM4fXg7mDRjfiD5k");
        }
        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    insertItemIntoDatabase();
                } else {
                    Toast.makeText(NewAdd.this, "PLEASE FILL IN ALL THE REQUIRED INPUT FIELDS", Toast.LENGTH_SHORT).show();
                }
            }

        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Button lcoationdetails = findViewById(R.id.buttonlocation);
        lcoationdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(NewAdd.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    locationpermissionrequest();
                } else {
                    gettingcurrentlocation();
                }
            }
        });
        location.setFocusable(false);
        location.setOnClickListener(v -> {
            List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS);
            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                    .build(this);
            startActivityForResult(intent, 100);
        });
    }
    private boolean validateFields() {
        // Check if any of the fields is empty
        return !name.getText().toString().isEmpty() &&
                !phone.getText().toString().isEmpty() &&
                !desc.getText().toString().isEmpty() &&
                !date.getText().toString().isEmpty() &&
                !location.getText().toString().isEmpty();
    }
    private void insertItemIntoDatabase() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String type = selectedRadioButton.getText().toString();
        String nameValue = name.getText().toString();
        String phoneValue = phone.getText().toString();
        String descValue = desc.getText().toString();
        String dateValue = date.getText().toString();
        String locationValue = location.getText().toString();
        boolean inserted = databasehelper.addItem(nameValue, descValue, locationValue, dateValue, type,phoneValue);
        if (inserted) {
            Toast.makeText(NewAdd.this, "ITEM ADDED", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(NewAdd.this, "FAILED", Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent(NewAdd.this, MainActivity.class);
        startActivity(i);
    }
    private void showDatePickerDialog() {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Using SimpleDateFormat to format the date.
                        Calendar dateCal = Calendar.getInstance();
                        dateCal.set(Calendar.YEAR, year);
                        dateCal.set(Calendar.MONTH, monthOfYear);
                        dateCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
                        date.setText(dateFormat.format(dateCal.getTime()));
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
    private void locationpermissionrequest() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int req_Code, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(req_Code, permissions, grantResults);
        if (req_Code == REQUEST_LOCATION_PERMISSION && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            gettingcurrentlocation();
        } else {
            Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("MissingPermission")
    private void gettingcurrentlocation() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location locat) {
                        if (locat != null) {
                            curentloc = locat;
                            convertCoordinatesToLocation(locat.getLatitude(), locat.getLongitude());
                        } else {
                            Toast.makeText(NewAdd.this, "No location available", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NewAdd.this, "Error getting location", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    protected void onActivityResult(int req_Code, int resultCode, @Nullable Intent data) {
        super.onActivityResult(req_Code, resultCode, data);
        if (req_Code == 100 && resultCode == RESULT_OK && data != null) {
            Place place = Autocomplete.getPlaceFromIntent(data);
            location.setText(place.getAddress());
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            Status loc_status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(this, "Error: " + loc_status.getStatusMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void convertCoordinatesToLocation(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                String addressastext = address.getAddressLine(0);
                location.setText(addressastext);
            } else {
                Toast.makeText(this, "No address found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, "Unable to geocode latitude and longitude", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShowOnMap extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap google_map;
    List<ItemModel> itemdetails = new ArrayList<>();
    DataBaseHelper databasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_on_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentmap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        google_map = googleMap;
        showlocationonmap();
    }

    private void showlocationonmap() {
        databasehelper= new DataBaseHelper(this);
        Cursor ca= databasehelper.fetchAllItems();
        if (ca.getCount()==0){
            Toast.makeText(this, "NO ITEMS", Toast.LENGTH_SHORT).show();
        }else{
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            while (ca.moveToNext()){
                ItemModel i=new ItemModel(ca.getString(0),ca.getString(1),ca.getString(2),ca.getString(3),ca.getString(4),ca.getString(5),ca.getString(6));
                itemdetails.add(i);
                String location_Name = i.getItemlocation();
                addmarkeronmap(location_Name, builder);
            }
            ca.close();
            google_map.setOnMapLoadedCallback(() -> {
                LatLngBounds bounds = builder.build();
                google_map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
            });
        }
    }

    private void addmarkeronmap(String location_Name, LatLngBounds.Builder builder) {
        Geocoder geocoder_obj = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses_obj = geocoder_obj.getFromLocationName(location_Name, 1);
            if (!addresses_obj.isEmpty()) {
                Address address_obj = addresses_obj.get(0);
                LatLng position = new LatLng(address_obj.getLatitude(), address_obj.getLongitude());
                builder.include(position);
                runOnUiThread(() -> google_map.addMarker(new MarkerOptions()
                        .position(position)
                        .title(location_Name)));
            }
        } catch (IOException e) {
            runOnUiThread(() -> Toast.makeText(ShowOnMap.this, "Error geocoding: " + location_Name, Toast.LENGTH_SHORT).show());
        }
    }
}
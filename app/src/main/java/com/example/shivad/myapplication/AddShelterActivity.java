package com.example.shivad.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Jacob on 2/25/2018.
 */

public class AddShelterActivity extends AppCompatActivity {
    private EditText name, capacity, restrictions, longitude, latitude, address, notes, phone;
    private Button submit, toMain;
    private static int manualShelter = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shelter);


        name = (EditText) findViewById(R.id.etName);
        capacity = (EditText) findViewById(R.id.etCapacity);
        restrictions = (EditText) findViewById(R.id.etRestrictions);
        longitude = (EditText) findViewById(R.id.etLongitude);
        latitude = (EditText) findViewById(R.id.etLatitude);
        address = (EditText) findViewById(R.id.etAddress);
        notes = (EditText) findViewById(R.id.etSpecialNotes);
        phone = (EditText) findViewById(R.id.etPhoneNumber);

        submit = (Button) findViewById(R.id.bSubmit);
        toMain = (Button) findViewById(R.id.bBack);

        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(AddShelterActivity.this, MainActivity.class);
                AddShelterActivity.this.startActivity(registerIntent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameString = name.getText().toString();
                String capacityString = capacity.getText().toString();
                String[] restrictionsString = restrictions.getText().toString().trim().toLowerCase().split("\\/(?! )");
                String longitudeString = longitude.getText().toString();
                String latitudeString = latitude.getText().toString();
                String addressString = address.getText().toString();
                String notesString = notes.getText().toString();
                String phoneString = phone.getText().toString();

                if (nameString.length() <= 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddShelterActivity.this);
                    builder.setMessage("Please enter shelter name.")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                } else if (addressString.length() <= 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddShelterActivity.this);
                    builder.setMessage("Please enter shelter address.")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                } else {
                    if (capacityString.equals("")) {
                        capacityString += 0;
                    }
                    if (longitudeString.equals("")) {
                        longitudeString += 0;
                    }
                    if (latitudeString.equals("")) {
                        latitudeString += 0;
                    }
                    ShelterList shelterList = ShelterList.getInstance();
                    shelterList.addShelter(new Shelter(
                            manualShelter++,
                            nameString,
                            Integer.parseInt(capacityString),
                            restrictionsString,
                            Double.parseDouble(longitudeString),
                            Double.parseDouble(latitudeString),
                            addressString,
                            notesString,
                            phoneString
                    ));
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddShelterActivity.this);
                    builder.setMessage("Shelter has been successfully added.")
                            .setNegativeButton("Return Home", null)
                            .create()
                            .show();
                    Intent returnHome = new Intent(AddShelterActivity.this, MainActivity.class);
                    AddShelterActivity.this.startActivity(returnHome);
                }
            }
        });
    }
}

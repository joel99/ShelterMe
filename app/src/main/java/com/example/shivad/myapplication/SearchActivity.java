package com.example.shivad.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by joel on 3/3/18.
 */

// This is just the search form
public class SearchActivity extends AppCompatActivity {
    private EditText shelterNameText;
    private Spinner spinGender;
    private Spinner spinAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shelters);
        // Attach
        shelterNameText = findViewById(R.id.tShelterName);
        Button returnButton = findViewById(R.id.bReturn);
        Button searchButton = findViewById(R.id.bSearch);
        spinGender = findViewById(R.id.spinGender);
        spinAge = findViewById(R.id.spinAge);

        // Populate spinners
        String[] genderSpinnerOps = new String[] {
                "Male", "Female", "Unspecified"
        };
        String[] ageSpinnerOps = new String[] {
                "Families with Newborns", "Children", "Young Adults", "Anyone", "Unspecified"
        };
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, genderSpinnerOps);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinGender.setAdapter(genderAdapter);

        ArrayAdapter<String> ageAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ageSpinnerOps);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAge.setAdapter(ageAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepSearch();
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void prepSearch()  {
        Intent searchIntent = new Intent(this, ShelterActivity.class);
        String nameText = shelterNameText.getText().toString().trim();
        String ageGroup = spinAge.getSelectedItem().toString();
        String gender = spinGender.getSelectedItem().toString();
        String userEmail = getIntent().getStringExtra("userEmail");
        searchIntent.putExtra("name", nameText);
        searchIntent.putExtra("ageGroup", ageGroup);
        searchIntent.putExtra("gender", gender);
        searchIntent.putExtra("userEmail", userEmail);
        startActivity(searchIntent);
    }


}

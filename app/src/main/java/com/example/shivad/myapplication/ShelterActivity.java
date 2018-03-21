package com.example.shivad.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * Created by Jacob on 2/25/2018.
 */

public class ShelterActivity extends AppCompatActivity {
    private ListView shelterListView;
    private Button returnButton;
    private ShelterList shelterList;
    private String m_Text = "0";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shelter_list);
        shelterListView = (ListView) findViewById(R.id.shelterListView);
        returnButton = (Button) findViewById(R.id.returnButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();

        shelterList = ShelterList.getInstance();
        Shelter[] shelterArr = shelterList.toArray();
        // filter here
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String nameFilter = "";
        String genderFilter = "Anyone";
        String ageGroupFilter = "Anyone";
        if (extras != null) {
            if (extras.containsKey("name")) {
                nameFilter = extras.getString("name");
            }
            if (extras.containsKey("ageGroup")) {
                ageGroupFilter = extras.getString("ageGroup");
            }
            if (extras.containsKey("gender")) {
                genderFilter = extras.getString("gender");
            }
        }

        // Streams are not supported shoot
        final ArrayList<Shelter> filteredShelters = new ArrayList<>();
        for (int i = 0; extras != null && extras.size() > 1 && i < shelterArr.length; i++) {
            if (shelterArr[i].matchRestrictions(nameFilter, genderFilter, ageGroupFilter)) {
                filteredShelters.add(shelterArr[i]);
            }
        }
        if (extras == null || extras.size() == 1) {
            Collections.addAll(filteredShelters, shelterArr);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredShelters);
        shelterListView.setAdapter(adapter);
        shelterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShelterActivity.this);
                builder.setMessage(filteredShelters.get(position).getMessage())
                        .setNegativeButton("Exit", null);

                        if(UserList.getInstance().checkUser(getIntent().getStringExtra("userEmail")) != null) {
                            builder.setMessage(filteredShelters.get(position).getMessage())
                                    .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            AlertDialog.Builder builder2 = new AlertDialog.Builder(ShelterActivity.this);
                                            final EditText input = new EditText(ShelterActivity.this);
                                            builder2.setView(input);
                                            builder2.setMessage("How many People do you want to sign in?")
                                                    .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            m_Text = input.getText().toString();
                                                            try {
                                                                if (!filteredShelters.get(position).add(Integer.parseInt(m_Text))) {
                                                                    AlertDialog.Builder build = new AlertDialog.Builder(ShelterActivity.this);
                                                                    build.setMessage("Invalid input or not enough space")
                                                                            .setNegativeButton("Exit", null)
                                                                            .show();
                                                                }
                                                                UserList.getInstance().checkUser(getIntent().getStringExtra("userEmail"))
                                                                        .setShelter(filteredShelters.get(position), Integer.parseInt(m_Text));
                                                            }
                                                            //catch(Exception n) {
                                                            catch (NumberFormatException n) {
                                                                AlertDialog.Builder build = new AlertDialog.Builder(ShelterActivity.this);
                                                                build.setMessage("Invalid input")
                                                                        .setNegativeButton("Exit", null)
                                                                        .show();
                                                            }

                                                        }
                                                    })
                                                    .setNegativeButton("Cancel", null).show();
                                        }
                                    }).show();
                        }
                        else {
                            builder.show();
                        }
                /*
                AlertDialog.Builder builder2 = new AlertDialog.Builder(ShelterActivity.this);
                final EditText input = new EditText(ShelterActivity.this);
                builder2.setView(input);
                builder2.setMessage("How many People do you want to sign in?")
                        .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                m_Text = input.getText().toString();
                            }})
                        .setNegativeButton("Cancel", null).show();

                if(!filteredShelters.get(position).add(Integer.parseInt(m_Text))) {
                    AlertDialog.Builder build = new AlertDialog.Builder(ShelterActivity.this);
                    builder.setMessage("Invalid input or not enough space")
                            .setNegativeButton("Exit", null)
                            .create();
                }*/
                }
        });


    }
}

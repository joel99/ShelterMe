package com.example.shivad.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jacob on 2/25/2018.
 */

public class ShelterActivity extends AppCompatActivity {
    private ListView shelterListView;
    private String m_Text = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shelter_list);
        Button mapButton = findViewById(R.id.toMap);
        shelterListView = findViewById(R.id.shelterListView);
        Button returnButton = findViewById(R.id.returnButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent viewMap = new Intent(ShelterActivity.this, MapsActivity.class);
                //String s = getIntent().getStringExtra("userEmail");
                //viewMap.putExtra("userEmail", s);
                Intent intent = getIntent();
                Bundle extras = intent.getExtras();
                //final List<Shelter> filteredShelters = getFiltered(shelterArr,extras);
                if(extras != null) {
                    viewMap.putExtras(extras);
                }
                ShelterActivity.this.startActivity(viewMap);
            }
        });
    }

    /**
     * Returns a list of the shelters matching the parameters
     * @param shelterArr the array of shelters
     * @param extras extra shelters
     * @return a list of the shelters
     */
    public static List<Shelter> getFiltered(Shelter[] shelterArr, Bundle extras) {
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
        final List<Shelter> filteredShelters = new ArrayList<>();
        for (int i = 0; extras != null && extras.size() > 1 && i < shelterArr.length; i++) {
            if (shelterArr[i].matchRestrictions(nameFilter, genderFilter, ageGroupFilter)) {
                filteredShelters.add(shelterArr[i]);
            }
        }
        if (extras == null || extras.size() == 1) {
            Collections.addAll(filteredShelters, shelterArr);
        }
        return filteredShelters;
    }
    @Override
    protected void onResume() {
        super.onResume();

        ShelterList shelterList = ShelterList.getInstance();
        Shelter[] shelterArr = shelterList.toArray();
        // filter here
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final List<Shelter> filteredShelters = getFiltered(shelterArr,extras);

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

                }
        });


    }
}

package com.example.shivad.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button searchShelters;
    private Button viewShelters;
    private Button addShelters;
    private Button logOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchShelters = (Button) findViewById(R.id.bSearchShelters);
        viewShelters = (Button) findViewById(R.id.bViewShelters);
        logOut = (Button) findViewById(R.id.bLogOut);
        addShelters = (Button) findViewById(R.id.bAddShelter);

        searchShelters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewSearchShelters = new Intent(MainActivity.this, SearchActivity.class);
                String s = getIntent().getStringExtra("userEmail");
                viewSearchShelters.putExtra("userEmail", s);
                MainActivity.this.startActivity(viewSearchShelters);
            }
        });
        viewShelters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewShelterList = new Intent(MainActivity.this, ShelterActivity.class);
                String s = getIntent().getStringExtra("userEmail" );
                viewShelterList.putExtra("userEmail", s);
                MainActivity.this.startActivity(viewShelterList);
            }
        });
        addShelters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addShelter = new Intent(MainActivity.this, AddShelterActivity.class);
                //String s = getIntent().getStringExtra("userEmail" );
                //addShelter.putExtra("userEmail", s);
                MainActivity.this.startActivity(addShelter);

            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToLoginPage = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(backToLoginPage);
            }
        });

    }
}

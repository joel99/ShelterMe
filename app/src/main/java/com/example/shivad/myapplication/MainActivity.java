package com.example.shivad.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button searchShelters = findViewById(R.id.bSearchShelters);
        Button viewShelters = findViewById(R.id.bViewShelters);
        Button logOut = findViewById(R.id.bLogOut);

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
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToLoginPage = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(backToLoginPage);
            }
        });

    }
}

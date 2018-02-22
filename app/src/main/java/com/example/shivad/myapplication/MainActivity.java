package com.example.shivad.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView logInSucess;
    private Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logInSucess = (TextView) findViewById(R.id.messageLogInSuccess);
        logOut = (Button) findViewById(R.id.bLogOut);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToLoginPage = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(backToLoginPage);
            }
        });

    }
}

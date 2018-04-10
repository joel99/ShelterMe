package com.example.shivad.myapplication;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;


public class LoginActivity extends AppCompatActivity {

    private EditText userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //DON'T DELETE THESE LINES OR CHANGE ORDER, IT MAKES IT WORK FOR SOME REASON
        ShelterList shelterList = ShelterList.getInstance();
        if(shelterList.size() == 0)
        for(int i = 0; i < 500000000; i++) {}
        UserList userList = UserList.getInstance();

        userEmail = findViewById(R.id.etEmail);
        userPassword = findViewById(R.id.etPassword);
        Button userLogIn = findViewById(R.id.bLogIn);
        Button toRegister = findViewById(R.id.bToRegister);
        Button viewShelters = findViewById(R.id.bViewNoLogin);

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });


        userLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userEmailString = userEmail.getText().toString();
                final String userPasswordString = userPassword.getText().toString();

                if (userEmailString.length() <= 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Please enter your email.")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                } else if (userPasswordString.length() <= 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Please enter your password.")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                } else {
                    if (userEmailString.equals("user") && userPasswordString.equals("pass")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Login Successful!")
                                .create()
                                .show();
                        LoginActivity.this.startActivity(new Intent (LoginActivity.this, MainActivity.class));
                    } else {
                        UserList userList = UserList.getInstance();

                        User _userExist = userList.checkUser(userEmailString);
                        if (_userExist == null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("The email you entered is not associated with a ShelterMe account.")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        } else {
                            if (_userExist.getPassword().equals(userPasswordString)) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Successful!")
                                        .create()
                                        .show();
                                Intent i = new Intent (LoginActivity.this, MainActivity.class);
                                i.putExtra("userEmail", _userExist.getEmail());
                                LoginActivity.this.startActivity(i);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Password is incorrect.")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        }
                    }
                }
            }
        });

        viewShelters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewShelterList = new Intent(LoginActivity.this, ShelterActivity.class);
                LoginActivity.this.startActivity(viewShelterList);
            }
        });
    }

}


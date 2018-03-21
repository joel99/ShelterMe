package com.example.shivad.myapplication;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;

/**
 * A Register screen that offers register via email/password.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText newEmail, newPassword, confirmPassword;
    private Spinner userTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        newEmail = (EditText) findViewById(R.id.etNewEmail);
        newPassword = (EditText) findViewById(R.id.etNewPassword);
        confirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        userTypeSpinner = (Spinner) findViewById(R.id.spinUserType);

        ArrayAdapter<String> adapterForUserType = new ArrayAdapter(this, android.R.layout.simple_spinner_item, UserType.values());
        adapterForUserType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapterForUserType);

        findViewById(R.id.bRegister).setOnClickListener(this);
        findViewById(R.id.bCancel).setOnClickListener(this);
    }

    private void registerUser() {
        String email = newEmail.getText().toString().trim();
        String password = newPassword.getText().toString().trim();
        String confirmPass = confirmPassword.getText().toString().trim();

        if (email.isEmpty()) {
            newEmail.setError("Email is required.");
            newEmail.requestFocus();
            return;
        } else if (password.isEmpty()) {
            newPassword.setError("Password is required.");
            newPassword.requestFocus();
            return;
        } else if (!confirmPass.equals(password)) {
            confirmPassword.setError("Password does not match. " +
                    "Please enter the same password as you provided above.");
            confirmPassword.requestFocus();
            return;
        }

        User _user = new User(email, password, (UserType) userTypeSpinner.getSelectedItem());

        UserList userList = UserList.getInstance();

        if (userList.addUser(_user) == false) {

            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setMessage("The email you entered is already associated with a ShelterMe account.")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bRegister) {
            registerUser();
        } else if (view.getId() == R.id.bCancel) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}

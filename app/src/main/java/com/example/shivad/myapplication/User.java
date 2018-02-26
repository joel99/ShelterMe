package com.example.shivad.myapplication;

/**
 * Created by JihwanK on 2/21/18.
 */

public class User {
    private String _email;
    private String _password;
    private UserType _userType;

    public User(String email, String password, UserType userType) {
        _email = email;
        _password = password;
        _userType = userType;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public UserType getUserType() {
        return _userType;
    }

    public void setUserType(UserType userType) {
        _userType = userType;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof User)) {
            return false;
        }
        return this._email.equals(((User) other).getEmail());
    }
}

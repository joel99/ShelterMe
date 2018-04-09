package com.example.shivad.myapplication;

import android.util.Log;

/**
 * Created by JihwanK on 2/21/18.
 */

public class User {
    private String _email;
    private String _password;
    private UserType _userType;
    private Shelter _shelter;
    private int _numShelter;
    private int _key;

    public User(String email, String password, UserType userType) {
        this(email, password, userType, 0);
    }
    public User(String email, String password, UserType userType, int key) {
        this(email, password, userType, key, null, 1);
    }
    public User(String email, String password, UserType userType, int key, Shelter s, int num) {
        _email = email;
        _password = password;
        _userType = userType;
        _key = key;
        _numShelter = num;
        _shelter = s;
    }

    @Override
    public String toString() {
        return _email;
    }

    public int get_key() {
        return _key;
    }

    public void set_key(int _key) {
        this._key = _key;
    }

    public void setShelter(Shelter s, int num) {
        if(_shelter != null) {
            _shelter.incVacancy(_numShelter);
            DBInterfacer.update(_shelter);
        }
        _shelter = s;
        _numShelter = num;
        DBInterfacer.update(s);
        //ShelterList.updateDB();
        DBInterfacer.setUserShelter(this, s, num);
    }

    public Shelter getShelter() {
        return _shelter;
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

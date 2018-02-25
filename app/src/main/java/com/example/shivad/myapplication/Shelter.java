package com.example.shivad.myapplication;

import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by Jacob on 2/25/2018.
 */

public class Shelter {
    private final int _key;     // 0
    private String _name;       // 1
    private int _capacity;      // 2
    private String _gender;     // 3
    private double _longitude;  // 4
    private double _latitude;   // 5
    private String _address;    // 6
    private String _phoneNumber;// 7

    public Shelter(int key, String name, int capacity, String gender, double longitude,
                   double latitude, String address, String phoneNumber) {
        _key = key;
        _name = name;
        _capacity = capacity;
        _gender = gender;
        _longitude = longitude;
        _latitude = latitude;
        _address = address;
        _phoneNumber = phoneNumber;
    }
    public int getKey() {
        return _key;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getCapacity() {
        return _capacity;
    }

    public void setCapacity(int capacity) {
        _capacity = capacity;
    }

    public String getGender() {
        return _gender;
    }

    public void setGender(String gender) {
        _gender = gender;
    }

    public double getLongitutde() {
        return _longitude;
    }

    public void setLongitude(double longitude) {
        _longitude = longitude;
    }

    public double getLatitude() {
        return _latitude;
    }

    public void setLatitude(double latitude) {
        _latitude = latitude;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        _address = address;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        _phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals (Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof Shelter)) {
            return false;
        }
        return this._key == ((Shelter) other).getKey();
    }

    @Override
    public String toString() {
        String str = _name + "\n";
        str += "Capacity: " + _capacity + "\n";
        str += "Latitude: " + _latitude + "\n";
        str += "Longitude: " + _longitude + "\n";
        str += "Address: " + _address + "\n";
        str += "Phone: " + _phoneNumber + "\n";
        return str;
    }
}

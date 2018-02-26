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
    public final int _key;         // 0
    private String _name;           // 1
    private int _capacity;          // 2
    private String _gender;         // 3
    private double _longitude;      // 4
    private double _latitude;       // 5
    private String _address;        // 6
    private String _specialNotes;   // 7
    private String _phoneNumber;    // 8

    public Shelter(int key, String name, int capacity, String gender, double longitude,
                   double latitude, String address, String specialNotes, String phoneNumber) {
        _key = key;
        _name = name;
        _capacity = capacity;
        _gender = gender;
        _longitude = longitude;
        _latitude = latitude;
        _address = address;
        _specialNotes = specialNotes;
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
        return this._key == ((Shelter) other)._key;
    }

    public String getName() {
        return _name;
    }

    @Override
    public String toString() {
        String str = _name + "\n\n";
        str += "Capacity: " + _capacity + "\n";
        str += "Restrictions: " + _gender + "\n";
        str += "Latitude: " + _latitude + "\n";
        str += "Longitude: " + _longitude + "\n";
        str += "Address: " + _address + "\n";
        str += "Phone: " + _phoneNumber + "\n";
        str += "Special Notes: " + _specialNotes;
        return str;
    }
}

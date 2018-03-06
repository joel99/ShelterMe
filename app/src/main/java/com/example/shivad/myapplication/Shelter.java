package com.example.shivad.myapplication;

import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jacob on 2/25/2018.
 */

public class Shelter {
    public final int _key;          // 0
    private String _name;           // 1
    private int _capacity;          // 2
    private ArrayList<String> _restrictions;   // 3
    private double _longitude;      // 4
    private double _latitude;       // 5
    private String _address;        // 6
    private String _specialNotes;   // 7
    private String _phoneNumber;    // 8

    public Shelter(int key, String name, int capacity, String[] restrictions, double longitude,
                   double latitude, String address, String specialNotes, String phoneNumber) {
        _key = key;
        _name = name;
        _capacity = capacity;
        _restrictions = new ArrayList<String>(Arrays.asList(restrictions));
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

    @Override
    public String toString() {
        return _name;
    }

    public String getMessage() {
        String str = _name + "\n\n";
        str += "Capacity: " + _capacity + "\n";
        String rString = "";
        if (_restrictions.size() != 0) {
            rString = _restrictions.get(0);
            for (int i = 1; i < _restrictions.size(); i++) {
                rString += ", " + _restrictions.get(i);
            }
        }
        str += "Restrictions: " + rString + "\n";
        str += "Latitude: " + _latitude + "\n";
        str += "Longitude: " + _longitude + "\n";
        str += "Address: " + _address + "\n";
        str += "Phone: " + _phoneNumber + "\n";
        str += "Special Notes: " + _specialNotes;
        return str;
    }

    // VERY HARDCODED...
    public Boolean matchRestrictions(String name, String gender, String ageGroup) {
        // Name match
        name = name.toLowerCase();
        gender = gender.toLowerCase();
        ageGroup = ageGroup.toLowerCase();
        // TODO: FUZZY SEARCH
        if (!_name.toLowerCase().contains(name)) return false;
        if (_restrictions.contains("anyone") || _restrictions.size() == 0) return true;
        // Gender restrictions
        // ONLY HAVE TO MATCH ONE OF GENDER OR AGE RESTRICT (OUR POLICY)
        // UNLESS ONLY ONE FLAG SPECIFIED... THEN THE OTHER ONE IS MORE IMPORTANT TO MATCH
        boolean genderFlag = true;
        boolean ageFlag = true;
        if (!(_restrictions.contains("women") || _restrictions.contains("men"))) {
            genderFlag = false; // we don't care about gender
        }
        if (_restrictions.contains("women") && !gender.equals("female")) { // if restrictions doesn't mention, it's fine
            genderFlag = false;
        } else if (_restrictions.contains("men") && !gender.equals("male")) {
            genderFlag = false;
        }
        genderFlag = genderFlag || gender.equals("unspecified");
        // Age restrictions
        boolean familyFlag = false;
        for (int i = 0; i < _restrictions.size(); i++) {
            if (_restrictions.get(i).contains("famil")) {
                familyFlag = true;
            }
        }
        if (!(familyFlag || _restrictions.contains("children") || _restrictions.contains("childrens") || _restrictions.contains("young adults"))) {
            ageFlag = false;
        }
        if ((_restrictions.contains("children") || _restrictions.contains("childrens")) && !ageGroup.equals("children")) {
            // Edge case here: allow women/children to go through if you're women
            ageFlag = false;
        } else if (_restrictions.contains("young adults") && !ageGroup.equals("young adults")) {
            ageFlag = false;
        }
        // Difficult family case
        if (familyFlag && !ageGroup.equals("families with newborns")) {
            ageFlag = false;
        }
        ageFlag = ageFlag || ageGroup.equals("unspecified");
        return genderFlag || ageFlag;
    }
}

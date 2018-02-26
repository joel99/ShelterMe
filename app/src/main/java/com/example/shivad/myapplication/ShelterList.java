package com.example.shivad.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2/25/2018.
 */

public class ShelterList {
    private static final ShelterList _instance = new ShelterList();

    public static ShelterList getInstance() {
        return _instance;
    }

    private List<Shelter> shelterList;

    private ShelterList() {
        shelterList = new ArrayList<>();
    }

    public boolean addShelter(Shelter shelter) {
        for (Shelter s: shelterList) {
            if (shelter.equals(s)) {
                return false;
            }
        }
        shelterList.add(shelter);
        return true;
    }

    public Shelter get(int i) {
        return shelterList.get(i);
    }

    public int size() {
        return shelterList.size();
    }
    public Shelter[] toArray() {
        return shelterList.toArray(new Shelter[shelterList.size()]);
    }
}

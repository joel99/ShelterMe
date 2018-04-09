package com.example.shivad.myapplication;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jacob on 4/9/18.
 */
public class ShelterTest {
    private Shelter shelter;

    @Before
    public void setUp() throws Exception {
        String[] s = {};
        shelter = new Shelter(1, "5th st Shelter", 200, s, 0, 0, "840 Techwood Dr. Atlanta Ga, 30313",
                "","2018719367");
    }

    @Test
    public void addShelter() {
        ShelterList instance = ShelterList.getInstance();
        // returns true when shelter not already in database
        assertTrue(instance.addShelter(shelter));
        List<Shelter> list = instance.getList();
        // shelter added to list
        assertTrue(list.contains(shelter));
        // returns false because shelter already on the list
        assertFalse(instance.addShelter(shelter));
    }
}
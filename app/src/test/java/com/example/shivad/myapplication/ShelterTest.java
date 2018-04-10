package com.example.shivad.myapplication;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jacob on 4/9/18.
 */
public class ShelterTest {
    private Shelter shelter;
    private Shelter shelter2;
    private Shelter shelter3;
    @Before
    public void setUp() throws Exception {
        String[] s = {};
        shelter = new Shelter(1, "5th st Shelter", 200, 150,s, 0, 0, "840 Techwood Dr. Atlanta Ga, 30313",
                "","2018719367");

        shelter2 = new Shelter(2, "5th st Shelter", 200, 150, s, 0, 0, "840 Techwood Dr. Atlanta Ga, 30313",
                "","2018719367");

        shelter3 = new Shelter(1, "5th st Shelter", 200, 100, s, 0, 0, "840 Techwood Dr. Atlanta Ga, 30313",
                "","2018719367");
    }

    @Test
    public void equals() {
        assertFalse(shelter.equals(shelter2));
        assertFalse(shelter2.equals(shelter));
        assertTrue(shelter.equals(shelter3));
        assertFalse(shelter.equals(null));
    }
}
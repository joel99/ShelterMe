package com.example.shivad.myapplication;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Branch Whitebox testing on the method add from the Shelter
 * Created by ShivaD on 4/9/18.
 */

public class ShelterVacancyTest {
    private Shelter s1;
    private Shelter s2;
    private Shelter s3;
    @Before
    public void setUp() {
        s1 = new Shelter(0, "S1", 0, 0, new String[0], 0.0, 0.0, "123 Park Place", "7187558248", "no notes");
        s2 = new Shelter(0, "S2", 10, 10, new String[0], 0.0, 0.0, "123 Park Place", "7187558248", "no notes");
        s3 = new Shelter(0, "S3", 10, 0, new String[0], 0.0, 0.0, "123 Park Place", "7187558248", "no notes");

    }
    @Test(timeout = 100)
    public void iTooSmall() {
        //Shows that tests return false and does not change vacancy if i is less than or equal to 0
        assertFalse(s1.add(-1));
        assert(s1.get_vacancy() == 0);
        assertFalse(s2.add(-2));
        assert(s2.get_vacancy() == 10);
        assertFalse(s3.add(-3));
        assert(s3.get_vacancy() == 0);
        assertFalse(s1.add(0));
        assert(s1.get_vacancy() == 0);
        assertFalse(s2.add(0));
        assert(s2.get_vacancy() == 10);
        assertFalse(s3.add(0));
        assert(s3.get_vacancy() == 0);
    }

    @Test(timeout = 100)
    public void iTooBig() {
        //Shows that tests return false and do not change vacancy if i > vacancy
        assertFalse(s1.add(11));
        assert(s1.get_vacancy() == 0);
        assertFalse(s2.add(11));
        assert(s2.get_vacancy() == 10);
        assertFalse(s3.add(11));
        assert(s3.get_vacancy() == 0);
        assertFalse(s1.add(1));
        assert(s1.get_vacancy() == 0);
        assertFalse(s2.add(100));
        assert(s2.get_vacancy() == 10);
        assertFalse(s3.add(1));
        assert(s3.get_vacancy() == 0);
    }

    @Test(timeout = 100)
    public void validInput() {
        //Shows that it returns true and changes vacancy with valid Input
        assert(s2.get_vacancy() == 10);
        assertTrue(s2.add(1));
        assert(s2.get_vacancy() == 9);
        assertTrue(s2.add(2));
        assert(s2.get_vacancy() == 6);
    }
}

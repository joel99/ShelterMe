package com.example.shivad.myapplication;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by joel on 4/9/18.
 * Tests "matchRestrictions" method of Shelter using the whitebox method (branch coverage)
 */

public class ShelterSearchTest {
    private ArrayList<Shelter> shelters;
    // matchRestrictions only cares about name, age, gender. Shelter internally only has name and restrictions array
    @Before
    public void setUp() throws Exception {
        // ArrayList<String> shelterNames = new ArrayList<>();
        ArrayList<String[]> shelterRestrictions = new ArrayList<>(); // branches in some semblance of order
        shelters = new ArrayList<>();
        shelterRestrictions.add(new String[]{"men", "children"}); // strict
        shelterRestrictions.add(new String[]{"anyone"}); // loose
        shelterRestrictions.add(new String[]{"men"}); // no age group
        shelterRestrictions.add(new String[]{"children"}); // no gender group
        shelterRestrictions.add(new String[]{"family with newborns"});
        shelterRestrictions.add(new String[]{"dummy age group"});
        shelterRestrictions.add(new String[]{"young adults"}); // no gender group
        for (int i = 0; i < shelterRestrictions.size(); i++) {
            shelters.add(new Shelter(0, "fake shelter", 0, 0, shelterRestrictions.get(i), 0.0, 0.0, "123 Park Place", "7187558248", "no notes"));
        }
    }

    @Test
    public void testMatchRestrictions() {

        assertEquals(true, true);

        // Name not matched (first branch true)
        assertEquals(false, shelters.get(0).matchRestrictions("not fake shelter", "dummy", "dummy"));
            // First branch false below
        // "anyone" restriction test (second branch true)
        assertEquals(true, shelters.get(1).matchRestrictions("fake", "dummy", "dummy"));
            // Second branch false below
        // Pseudo-blackbox testing
        /* GENDER TESTING */
        // When shelter doesn't care about gender - no match
        assertEquals( false, shelters.get(3).matchRestrictions("fake", "male", "dummy"));
        // When shelter cares about gender
            // gender group matches
        assertEquals( true, shelters.get(0).matchRestrictions("fake", "male", "dummy"));
            // gender group doesn't match
        assertEquals( false, shelters.get(0).matchRestrictions("fake", "female", "dummy"));
        /* AGE TESTING */
        // When shelter doesn't care about age - no match
        assertEquals( false, shelters.get(2).matchRestrictions("fake", "dummy", "children"));

        // No family or children mentioned l140 branch
        assertEquals(false, shelters.get(5).matchRestrictions("fake", "dummy", "children"));
        // Family mentioned (false case of l140)
        assertEquals(false, shelters.get(4).matchRestrictions("fake", "dummy", "children"));

        // l143 branch - restrict for children
        assertEquals( false, shelters.get(3).matchRestrictions("fake", "dummy", "young adults"));
        // l146 branch elif - restrict for young adults
        assertEquals(false, shelters.get(6).matchRestrictions("fake", "dummy", "children"));
        // else case below
        // l150 true case - requirements contains family but query doesn't
        assertEquals( false, shelters.get(4).matchRestrictions("fake", "dummy", "children"));
        // l150 false case - made it all the way through
        assertEquals( true, shelters.get(3).matchRestrictions("fake", "dummy", "children"));
        // testing "unspecified" age/gender restrictions (expects true for all)
        assertEquals(true, shelters.get(0).matchRestrictions("fake", "unspecified", "dummy"));
        assertEquals(true, shelters.get(0).matchRestrictions("fake", "dummy", "unspecified"));


    }

}

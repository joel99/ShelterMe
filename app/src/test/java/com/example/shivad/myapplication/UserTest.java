package com.example.shivad.myapplication;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JihwanK on 4/8/18.
 */
public class UserTest {
    private User kevin;

    @Before
    public void setUp() throws Exception {
        kevin = new User("kevin1234@gatech.edu", "123456", UserType.ADMIN);
    }

    @Test
    public void equals() {
        //When it is correct User
        assertEquals(true, kevin.equals(new User("kevin1234@gatech.edu", "123456", UserType.ADMIN)));

        //When the object passed in is null object or different type of object
        assertEquals(false, kevin.equals(null));
        assertEquals(false, kevin.equals(new String("kevin1234@gatech.edu")));

        //When the object passed in is not correct User
        assertEquals(false, kevin.equals(new User("kevin9876@gatech.edu", "123456", UserType.ADMIN)));
    }


}
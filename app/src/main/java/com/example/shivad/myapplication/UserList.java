package com.example.shivad.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JihwanK on 2/21/18.
 */

public class UserList {
    private static final UserList _instance = new UserList();

    /**
     * returns the instance of userlist
     * @return the instance of userlist
     */
    public static UserList getInstance() {
        return _instance;
    }

    private List<User> userList;

    private UserList() {
        userList = DBInterfacer.getUserVal();
        if(userList == null) {
            userList = new ArrayList<>();
        }
    }

    /**
     * adds user to UserList
     * @param user the user being added
     * @return true if the user is added. false if the user is already on the list
     */
    public boolean addUser(User user) {
        for (User u : userList) {
            if (user.equals(u)) {
                return false;
            }
        }
        userList.add(user);
        DBInterfacer.setUser(user);
        return true;
    }

    @Override
    public String toString() {
        return userList.toString();
    }

    /**
     * returns the lists size
     * @return the lists size
     */
    public int size() {
        return userList.size();
    }

    /**
     * returns instance as a list
     * @return instance as a list
     */
    public List<User> getList() {
        return userList;
    }

    /**
     * checks if a user is on the list
     * @param email the users email
     * @return whether or not the user is registered
     */
    public User checkUser(String email) {
        for (User u : userList) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
}

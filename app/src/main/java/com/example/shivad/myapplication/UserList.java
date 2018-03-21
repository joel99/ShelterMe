package com.example.shivad.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JihwanK on 2/21/18.
 */

public class UserList {
    private static final UserList _instance = new UserList();

    public static UserList getInstance() {
        return _instance;
    }

    private List<User> userList;

    private UserList() {
        userList = DBInterfacer.getUserVal();
        Log.d("hiiii", "DB -> UserList");
        //Log.d("hiiii",userList.get(0).getEmail());
        if(userList == null) {
            userList = new ArrayList<>();
        }
    }

    public boolean addUser(User user) {
        //Log.d("hiiii", "ListSize: " + userList.size());
        //userList = DBInterfacer.getUserVal();
        //Log.d("hiiii", "" + userList.size());
        for (User u : userList) {
            if (user.equals(u)) {
                return false;
            }
        }
        userList.add(user);
        DBInterfacer.setUser(user);
        Log.d("hiiii", "putInDB");
        return true;
    }

    @Override
    public String toString() {
        return userList.toString();
    }

    public int size() {
        return userList.size();
    }

    public List<User> getList() {
        return userList;
    }

    public User checkUser(String email) {
        //this.addUser(new User());
        //DBInterfacer.update();
        //Log.d("hiiii", "checkUser");
        //Log.d("hiiii", "" + userList.size());
        //userList = DBInterfacer.getUserVal();
        //Log.d("hiiii", "" + userList.size());
        for (User u : userList) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
}

package com.example.shivad.myapplication;

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
        userList = new ArrayList<>();
    }

    public boolean addUser(User user) {
        for (User u : userList) {
            if (user.equals(u)) {
                return false;
            }
        }
        userList.add(user);
        return true;
    }

    public User checkUser(String email) {
        for (User u : userList) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
}

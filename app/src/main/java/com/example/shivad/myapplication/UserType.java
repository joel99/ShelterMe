package com.example.shivad.myapplication;

/**
 * Created by JihwanK on 2/21/18.
 */

public enum UserType {
    HOMELESS("Homeless"), ADMIN("Admin");

    private String userType;

    public String getUserType() {
        return userType;
    }

    UserType(String user) {
        this.userType = user;
    }

    public static int findPosition(UserType user_Type) {
        int i = 0;
        if (user_Type.equals(HOMELESS)) {
            i = 0;
        } else if (user_Type.equals(ADMIN)) {
            i = 1;
        }
        return i;
    }
}

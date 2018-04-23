package com.example.shivad.myapplication;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by JihwanK on 4/20/18.
 */

public class UserLoginTrialMap {
    private static final UserLoginTrialMap _instance = new UserLoginTrialMap();

    /**
     * returns the instance of userlist
     * @return the instance of userlist
     */
    public static UserLoginTrialMap getInstance() {
        return _instance;
    }

    private Map<String, Integer> userTrialList;

    /**
     * Constructor for UserLoginTrialMap
     */
    private UserLoginTrialMap() {
        UserList userList = UserList.getInstance();
        userTrialList = new HashMap<>();
        for (User user: userList.getList()) {
            userTrialList.put(user.getEmail(), 3);
        }
    }

    /**
     * returns instance as a map
     * @return instance as a map
     */
    public Map<String, Integer> getMap() {
        return userTrialList;
    }

    /**
     * resetting the number of trials on the email address to 3 again
     * @param email
     */
    public void resetUserTrial(String email) {
        userTrialList.put(email, 3);
    }

    /**
     * decrease the amount of trials on the email by one.
     * if there is only one trial left, remove the email address from the map.
     * @param email user's email address
     */
    public void decreaseTrial(String email) {
        if (userTrialList.get(email) > 1) {
            userTrialList.put(email, userTrialList.get(email) - 1);
        } else {
            userTrialList.remove(email);
        }
    }

    /**
     * get amount of trial that is left on the email address
     *
     * @param email the user's email
     * @return null if email is null. otherwise, return the number of trials that is left
     */
    public Integer getValue(String email) {
        if (email == null) {
            return null;
        }
        return userTrialList.get(email);
    }
}

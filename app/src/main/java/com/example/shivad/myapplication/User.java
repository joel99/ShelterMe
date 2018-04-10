package com.example.shivad.myapplication;

/**
 * Created by JihwanK on 2/21/18.
 */

public class User {
    private String _email;
    private String _password;
    private UserType _userType;
    private Shelter _shelter;
    private int _numShelter;
    private int _key;

    /**
     * constructor for the user class
     * @param email the users email
     * @param password the users password
     * @param userType the users type
     */
    public User(String email, String password, UserType userType) {
        this(email, password, userType, 0);
    }

    /**
     * constructor for the user class
     * @param email the users email
     * @param password the users password
     * @param userType the users usertype
     * @param key the users id
     */
    public User(String email, String password, UserType userType, int key) {
        this(email, password, userType, key, null, 1);
    }
    /**
     * constructor for the user class
     * @param email the users email
     * @param password the users password
     * @param userType the users usertype
     * @param key the users id
     * @param s the shelter the user works at
     * @param num the number of shelters the user works at
     */
    public User(String email, String password, UserType userType, int key, Shelter s, int num) {
        _email = email;
        _password = password;
        _userType = userType;
        _key = key;
        _numShelter = num;
        _shelter = s;
    }

    @Override
    public String toString() {
        return _email;
    }

    /**
     * returns the users key
     * @return the users key
     */
    public int get_key() {
        return _key;
    }

    /**
     * sets the users key
     * @param _key the users new key
     */
    public void set_key(int _key) {
        this._key = _key;
    }

    /**
     * changes the vacancy of s
     * @param s the shelter being edited
     * @param num the number of vacancies in the shelter
     */
    public void setShelter(Shelter s, int num) {
        if(_shelter != null) {
            _shelter.incVacancy(_numShelter);
            DBInterfacer.update(_shelter);
        }
        _shelter = s;
        _numShelter = num;
        DBInterfacer.update(s);
        //ShelterList.updateDB();
        DBInterfacer.setUserShelter(this, s, num);
    }

    /**
     * returns the shelter the user works at
     * @return the shelter the user works at
     */
    public Shelter getShelter() {
        return _shelter;
    }

    /**
     * returns the users email
     * @return the users email
     */
    public String getEmail() {
        return _email;
    }

    /**
     * returns the users password
     * @return the users password
     */
    public String getPassword() {
        return _password;
    }

    /**
     * sets the users password
     * @param password the users new password
     */
    public void setPassword(String password) {
        _password = password;
    }

    /**
     * returns the users type
     * @return the users type
     */
    public UserType getUserType() {
        return _userType;
    }

    /**
     * sets the users type
     * @param userType the users new type
     */
    public void setUserType(UserType userType) {
        _userType = userType;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other != null && other instanceof User && this._email.equals(((User) other).getEmail());
    }
}

package com.example.shivad.myapplication;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by ShivaD on 3/11/18.
 */

public class DBInterfacer {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference shelterRef = database.getReference("shelter");
    static DatabaseReference userRef = database.getReference("user");

    public DBInterfacer() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //shelterRef.setValue(new ArrayList<Shelter>());

        /*myRef.setValue("Hello, World!");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("My activity", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("My Activity", "Failed to read value.", error.toException());
            }
        });*/
    }
    public static void setUser(User o) {
        ArrayList<User> diffNum = new ArrayList<>();

        for(int i=0; i<UserList.getInstance().getList().size(); i++){
            if(!diffNum.contains(UserList.getInstance().getList().get(i))){
                diffNum.add(UserList.getInstance().getList().get(i));
            }
        }
        o.set_key(diffNum.size() - 1);
        userRef.child("" + (diffNum.size() - 1)).setValue(o);
    }
    public static void setUser(Object o) {
        userRef.setValue(o);
    }
    public static void setVal(Shelter o) {
        ArrayList<Shelter> diffNum = new ArrayList<>();

        for(int i=0; i<ShelterList.getInstance().size(); i++) {
            if(!diffNum.contains(ShelterList.getInstance().getList().get(i))){
                diffNum.add(ShelterList.getInstance().getList().get(i));
            }
        }
        shelterRef.child("" + (diffNum.size() - 1)).setValue(o);
    }
    public static void setVal(Object o) {
        shelterRef.setValue(o);
    }
    public static LinkedList<Shelter> getVal() {
        final LinkedList<Shelter> ll =  new LinkedList<>();

        shelterRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String[] shelterInfo = userSnapshot.child("message").getValue(String.class).trim().split("\n");
                    Shelter shelter = new Shelter(userSnapshot.child("_key").getValue(Integer.class),
                            shelterInfo[0],
                            Integer.parseInt(shelterInfo[2].substring(10)),
                            Integer.parseInt(shelterInfo[3].substring(11)),
                            shelterInfo[4].substring(14).split(","),
                            Double.parseDouble(shelterInfo[5].substring(10)),
                            Double.parseDouble(shelterInfo[6].substring(11)),
                            shelterInfo[7].substring(9),
                            shelterInfo[8].substring(7),
                            shelterInfo[9].substring(8));
                    ll.add(shelter);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("My Activity", "Failed to read value.", error.toException());
            }
        });
        return ll;
    }
    public static LinkedList<User> getUserVal() {
        final LinkedList<User> ll =  new LinkedList<User>();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Shelter s;
                    int num;
                    try {
                        s = ShelterList.getInstance().get((userSnapshot.child("shelter").getValue(Integer.class)));
                        num = userSnapshot.child("num").getValue(Integer.class);
                    } catch(NullPointerException | IndexOutOfBoundsException e) {
                        s = null;
                        num = 0;
                    }
                    User user = new User(userSnapshot.child("email").getValue(String.class),
                            userSnapshot.child("password").getValue(String.class),
                            UserType.valueOf(userSnapshot.child("userType").getValue(String.class)),
                            Integer.parseInt(userSnapshot.getKey()), s, num);
                    ll.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("My Activity", "Failed to read value.", error.toException());
            }
        });
        return ll;
    }
    public static void setUserShelter(User u, Shelter s, int num) {
        userRef.child("" + u.get_key()).child("shelter").setValue(s._key);
        userRef.child("" + u.get_key()).child("num").setValue(num);
    }

    public static void update() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d("My activity", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w("My Activity", "Failed to read value.", error.toException());
            }
        });
    }

    public static void update(Shelter s) {
        shelterRef.child("" + s._key).setValue(s);
    }
}

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
    private static int userCount = 0;
    private static int shelterCount = 0;
    public DBInterfacer()
    {
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
        /*
        final long[] i = {0};
            //while (true) {
                //Log.d("hiiiii", database.getInstance(userRef.child("0").toString()).toString());
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                i[0] = dataSnapshot.getChildrenCount();
                Log.d("hiiii", "#users" + i[0]);
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
                //Log.d("hiiiii", "" + userRef.child("0").child("message"));
                //i[0]++;
            //}
        //while(userRef.child("" + i) != null) { i++;}
        //finally {
        Log.d("hiiii", "#users: " + i[0]);
            */
        ArrayList<User> diffNum = new ArrayList<>();

        for(int i=0; i<UserList.getInstance().getList().size(); i++){
            if(!diffNum.contains(UserList.getInstance().getList().get(i))){
                diffNum.add(UserList.getInstance().getList().get(i));
            }
        }
        //Set<User> uniques = new HashSet<User>(UserList.getInstance().getList());
        o.set_key(diffNum.size() - 1);
        userRef.child("" + (diffNum.size() - 1)).setValue(o);
        //}
        //userRef.child("" + userCount).setValue(o);
        //userCount++;
    }
    public static void setUser(Object o) {
        userRef.setValue(o);
    }
    public static void setVal(Shelter o) {
        //int i = 0;
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
        //Log.d("hiiii", "getVal");
        //o[0] = new LinkedList<User>();
        shelterRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.d("hiiii", "ondatachange");
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //Log.d("hiiii","key is: ");
                //Log.d("hiiii",dataSnapshot.getKey());
                //Log.d("hiiii", "#Child: " + dataSnapshot.getChildrenCount());
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    //Log.d("hiiii", "getVal" + userSnapshot.getValue());
                    String[] shelterInfo = userSnapshot.child("message").getValue(String.class).trim().split("\n");
                    //Log.d("hiiii", shelterInfo.toString());
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
                    /*(userSnapshot.child("email").getValue(String.class),
                            userSnapshot.child("password").getValue(String.class),
                            UserType.valueOf(userSnapshot.child("userType").getValue(String.class)));*/
                    ll.add(shelter);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("My Activity", "Failed to read value.", error.toException());
            }
        });
        //Log.d("hiiii", "List: " + ll.toString());
        return ll;
    }
    public static LinkedList<User> getUserVal() {
        final LinkedList<User> ll =  new LinkedList<User>();
        //o[0] = new LinkedList<User>();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //getVal();
                //Log.d("hiiii", "user ondatachange");
                //Log.d("hiiii", "Shelt Size: " + ShelterList.getInstance().size());
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //Log.d("hiiii","key is: ");
                //Log.d("hiiii",dataSnapshot.getKey());
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    //Log.d("hiiii", userSnapshot.child("shelter").getValue(Shelter.class).toString());
                    Shelter s;
                    int num;
                    try {
                        s = ShelterList.getInstance().get((userSnapshot.child("shelter").getValue(Integer.class)));
                        num = userSnapshot.child("num").getValue(Integer.class);
                        //Log.d("hiiii", "" + s.toString() + "  " + num);
                    } catch(NullPointerException | IndexOutOfBoundsException e) {
                        s = null;
                        num = 0;
                    }
                    User user = new User(userSnapshot.child("email").getValue(String.class),
                            userSnapshot.child("password").getValue(String.class),
                            UserType.valueOf(userSnapshot.child("userType").getValue(String.class)),
                            Integer.parseInt(userSnapshot.getKey()), s, num);
                    //Log.d("hiiii", "" + user.getShelter());
                    ll.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("My Activity", "Failed to read value.", error.toException());
            }
        });
        //Log.d("hiiii", "List: " + ll.toString());
        return ll;
    }
    public static void setUserShelter(User u, Shelter s, int num) {
        /*int i = 0;
        boolean checker = true;
        Log.d("hiiii", u.getEmail());
        Log.d("hiiii", userRef.child("" + i).child("email").equalTo("ar").on("child_added", function(snapshot) {
            Log.d("hiiii", snapshot.key);
        }));
        while(checker) {
            if (userRef.child("" + i).child("email").getKey().equals(u.getEmail())) {
                userRef.child("" + i).child("shelter").setValue(s);
                userRef.child("" + i).child("num").setValue(num);
                checker = false;
            }
        }*/
        userRef.child("" + u.get_key()).child("shelter").setValue(s._key);
        userRef.child("" + u.get_key()).child("num").setValue(num);
    }
    //Don't know if i need this yet and i have no idea how to test
    public static void update() {
        //myRef.setValue("Hello, World!");
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
        /*shelterRef.child("" + s._key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot. = s;
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
        });*/
    }
}

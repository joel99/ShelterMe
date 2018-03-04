package com.example.shivad.myapplication;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2/25/2018.
 */

public class ShelterList {
    private static final ShelterList _instance = new ShelterList();

    public static ShelterList getInstance() {
        return _instance;
    }

    private List<Shelter> shelterList;

    private ShelterList() {
        shelterList = new ArrayList<>();
    }

    public boolean addShelter(Shelter shelter) {
        for (Shelter s: shelterList) {
            if (shelter.equals(s)) {
                return false;
            }
        }
        shelterList.add(shelter);
        return true;
    }

    public Shelter get(int i) {
        return shelterList.get(i);
    }

    public int size() {
        return shelterList.size();
    }
    public Shelter[] toArray() {
        return shelterList.toArray(new Shelter[shelterList.size()]);
    }

    public static void loadSheltersFromFile(Context actContext) {
        BufferedReader br = null;
        try {
            //Open a stream on the raw file
            InputStream is = actContext.getResources().openRawResource(R.raw.homeless_shelter_db);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                line = line.replaceAll(",,", ", ,");

                //accounts for the commas between quotes
                String formattedLine = "";
                int index = 0;
                boolean betweenQuotes = false;


                boolean done = false;

                while(!done) {
                    System.out.println(formattedLine + "\t" + line.substring(index) + "\n----------");
                    if (betweenQuotes) {
                        formattedLine += line.substring(index, line.indexOf("\"", index)) + "\n";
                        index = line.indexOf("\"", index);
                        index = line.indexOf(",", index) + 1;
                        betweenQuotes = false;
                    } else {
                        if (line.indexOf("\"", index) >= 0) {
                            formattedLine += line.substring(index, line.indexOf("\"", index)).replaceAll(",","\n");
                            index = line.indexOf("\"", index) + 1;
                            betweenQuotes = true;
                        } else {
                            formattedLine += line.substring(index).replaceAll(",", "\n");
                            done = true;
                        }
                    }
                }

                String[] shelterInfo = formattedLine.trim().split("\n");
                String unparsedRestrictions = shelterInfo[3].trim().toLowerCase(); // presumably split on / with no space afterwards
                String[] restrictions = unparsedRestrictions.split("\\/(?! )");

                ShelterList.getInstance().addShelter(new Shelter(
                        Integer.parseInt(shelterInfo[0].trim()),            //key
                        shelterInfo[1].trim(),                              //name
                        Integer.parseInt("0" + shelterInfo[2].trim()),   //capacity
                        restrictions,                              //restrictions
                        Double.parseDouble(shelterInfo[4].trim()),          //latitude
                        Double.parseDouble(shelterInfo[5].trim()),          //longitude
                        shelterInfo[6].trim(),                              //address
                        shelterInfo[7].trim(),                              //etSpecialNotes
                        shelterInfo[8].trim()                               //etPhoneNumber
                ));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

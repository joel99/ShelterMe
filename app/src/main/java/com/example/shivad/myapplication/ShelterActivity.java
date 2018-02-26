package com.example.shivad.myapplication;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by Jacob on 2/25/2018.
 */

public class ShelterActivity extends AppCompatActivity {
    private ListView shelterListView;
    private Button returnButton;
    private ShelterList shelterList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shelter_list);

        shelterListView = (ListView) findViewById(R.id.shelterListView);
        returnButton = (Button) findViewById(R.id.returnButton);

        shelterList = ShelterList.getInstance();
        getSheltersFromFile(shelterList);
        Shelter[] shelterArr = shelterList.toArray();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, shelterArr);
        shelterListView.setAdapter(adapter);

        shelterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShelterActivity.this);
                builder.setMessage(shelterList.get(position).getMessage())
                        .setNegativeButton("Exit", null)
                        .create()
                        .show();
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getSheltersFromFile(ShelterList shelterList) {
        BufferedReader br = null;
        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.homeless_shelter_db);
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

                shelterList.addShelter(new Shelter(
                        Integer.parseInt(shelterInfo[0].trim()),            //key
                        shelterInfo[1].trim(),                              //name
                        Integer.parseInt("0" + shelterInfo[2].trim()),   //capacity
                        shelterInfo[3].trim(),                              //gender
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

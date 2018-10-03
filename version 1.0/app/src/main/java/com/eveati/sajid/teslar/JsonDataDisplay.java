package com.eveati.sajid.teslar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class JsonDataDisplay extends AppCompatActivity {

    // ArrayList for person names, email Id's and mobile numbers
    ArrayList<String> DeviceNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_data_display);
        // get the reference of RecyclerView
        ListView listView = (ListView) findViewById(R.id.list);
        // set a LinearLayoutManager with default vertical orientation
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        //listView.setLayoutManager(linearLayoutManager);

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            // fetch JSONArray named users
            JSONArray userArray = obj.getJSONArray("users");
            // implement for loop for getting users list data
            for (int i = 0; i < userArray.length(); i++) {

                // create a JSONObject for fetching single user data
                JSONObject userDetail = userArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
               // DeviceNames.add(userDetail.getString("Appliance_Name"));

                JSONObject name = userDetail.getJSONObject("sam");
                DeviceNames.add(name.getString("Appliance_Name"));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, DeviceNames));

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("Jsondata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

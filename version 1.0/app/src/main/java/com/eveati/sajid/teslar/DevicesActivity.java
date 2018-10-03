package com.eveati.sajid.teslar;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.eveati.sajid.teslar.Model.DatabaseHelper;

import org.json.JSONObject;

import java.util.ArrayList;

public class DevicesActivity extends AppCompatActivity {
    ListView ListView;
    Button ButtonAddMore,ButtonSchedule,ButtonHome;
    DatabaseHelper mDatabaseHelper;   // comment by sajid
    JSONObject dataObject,obj;
    private RequestQueue requestQueue;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        ListView = (ListView) findViewById(R.id.listViewName);


        ButtonAddMore = (Button) findViewById(R.id.buttonAddMore);
        ButtonSchedule = (Button) findViewById(R.id.buttonSchedule);
        ButtonHome = (Button) findViewById(R.id.buttonHome);
        mDatabaseHelper = new DatabaseHelper(this);

        requestQueue = Volley.newRequestQueue(this);
        populateListView();






                ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String name = adapterView.getItemAtPosition(i).toString();

                        Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                        int itemID = -1;
                        while(data.moveToNext()){
                            itemID = data.getInt(0);
                        }
                        if(itemID > -1){

                            Intent editScreenIntent = new Intent(DevicesActivity.this, EditDataActivity.class);
                            editScreenIntent.putExtra("id",itemID);
                            editScreenIntent.putExtra("name",name);
                            startActivity(editScreenIntent);
                        }
                        else{
                            toastMessage("No ID associated with that name");
                        }
                    }
                });









        ButtonAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DevicesActivity.this, SchedulingActivity.class);
                startActivity(intent);

            }
        });

        ButtonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              sendForScheduling();


                //Cursor data = mDatabaseHelper.getData();
               // Message.message(getApplicationContext(),data.getString(3));

            }
        });

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DevicesActivity.this, DashBoardActivity.class);
                startActivity(intent);

            }
        });











    }

    private void sendForScheduling() {


        Intent i = new Intent(this, WeeklySchedule.class);
        startActivity(i);



       // ToScheduler backgroundTask = new ToScheduler(this);
        //backgroundTask.execute();






    }







    private void populateListView() {

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }

        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        ListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){

                    Intent editScreenIntent = new Intent(DevicesActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });


    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }



}

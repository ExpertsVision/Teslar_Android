package com.eveati.sajid.teslar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.eveati.sajid.teslar.Model.DatabaseHelper;


public class MaintenanceModuleActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    JSONArray myarray;
    JSONObject innerjson;
    DatabaseHelper mDatabaseHelper;
    SharedPreferences sharedPreferences;

    Cursor ourdata;

    int  user_id_local;




    String url;


    String baseurl="http://writinggems.com/api/complaints/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_module);
        TextView textView1 = new TextView(getApplicationContext());

        requestQueue = Volley.newRequestQueue(this);

        mDatabaseHelper = new DatabaseHelper(this);

        sharedPreferences= getSharedPreferences("MyData", Context.MODE_PRIVATE);

        user_id_local=sharedPreferences.getInt("USERIDLOCAL",0);

        viewComplaints();


    }

    private void viewComplaints() {

        LoginUser backgroundTask = new LoginUser(this);
        backgroundTask.execute();
    }



    public class LoginUser extends AsyncTask<String, Void, String>
    {

        Context ctx;

        LoginUser(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... strings) {
            displayData();
            return null;
        }
    }

    private void displayData() {



        //userid=11;

        url=baseurl+user_id_local;
        //url="http://writinggems.com/api/complaints/11";


        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {



            @Override
            public void onResponse(JSONObject response) {



                // Parsing json object response
                // response will be a json object'2

                ArrayList <Freelancer> arrayList = new ArrayList<>();

                try {
                    myarray=response.getJSONArray("data");

                    for (int i=0;i<myarray.length();i++)
                    {
                        try {
                            innerjson=(JSONObject) myarray.get(i);



                            String complaintIds =innerjson.getString("Complaint_ID");
                            String titles =innerjson.getString("Title");
                            String description =innerjson.getString("Description");
                            String status =innerjson.getString("Status");
                            String submission_date =innerjson.getString("Submission_Date");
                            String lastvisit_date =innerjson.getString("Last_Visit_Date");
                            String remarks =innerjson.getString("Remarks");
                            String closed_date =innerjson.getString("Closing_Date");


                            arrayList.add(new Freelancer(complaintIds , titles , description , status , submission_date , lastvisit_date , remarks , closed_date ));







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    ListView listView = (ListView) findViewById(R.id.lisst);
                    FreelancerAdapter freelancerAdapter = new FreelancerAdapter(MaintenanceModuleActivity.this , arrayList);
                    listView.setAdapter(freelancerAdapter);

                    ListView listView1 = (ListView) findViewById(R.id.lisst1);
                    FreelancerHeaderAdapter freelancerHeaderAdapter = new FreelancerHeaderAdapter(MaintenanceModuleActivity.this , arrayList);
                    listView1.setAdapter(freelancerHeaderAdapter);









                } catch (JSONException e) {
                    e.printStackTrace();
                }







            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog

            }
        });
        requestQueue.add(jsonObjReq);



    }


    public void backtodashboard(View view) {
        opendash();
    }
    private void opendash(){
        Intent i = new Intent(this, DashBoardActivity.class);
        startActivity(i);

    }
}


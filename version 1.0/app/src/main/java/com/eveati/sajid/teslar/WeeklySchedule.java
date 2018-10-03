package com.eveati.sajid.teslar;



import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.Volley;
import com.eveati.sajid.teslar.Model.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class WeeklySchedule extends AppCompatActivity {


    DatabaseHelper mDatabaseHelper;   // comment by sajid
    private  JSONObject dataObject,myobj;
    private RequestQueue requestQueue;
    String url="http://18.222.102.183:9000/get_product";
    TextView TextViewMonday;

    // JSONArray myarray;
    // private String jsonResponse;


    ArrayList<String> DeviceNamesMon = new ArrayList<>();
    ArrayList<String> TimeMon = new ArrayList<>();
    ArrayList<String> DeviceNamesTue = new ArrayList<>();
    ArrayList<String> TimeTue = new ArrayList<>();
    ArrayList<String> DeviceNamesWed = new ArrayList<>();
    ArrayList<String> TimeWed = new ArrayList<>();
    ArrayList<String> DeviceNamesThur = new ArrayList<>();
    ArrayList<String> TimeThur = new ArrayList<>();
    ArrayList<String> DeviceNamesFri = new ArrayList<>();
    ArrayList<String> TimeFri = new ArrayList<>();
    ArrayList<String> DeviceNamesSat = new ArrayList<>();
    ArrayList<String> TimeSat = new ArrayList<>();
    ArrayList<String> DeviceNamesSun = new ArrayList<>();
    ArrayList<String> TimeSun = new ArrayList<>();
    private ListView lv1;
    private ListView lv2;
    private ListView lv3;

    private ListView lv4;
    private ListView lv5;
    private ListView lv6;

    private ListView lv7;
    private ListView lv8;
    private ListView lv9;

    private ListView lv10;
    private ListView lv11;
    private ListView lv12;

    private ListView lv13;
    private ListView lv14;
    private ListView lv15;

    private ListView lv16;
    private ListView lv17;
    private ListView lv18;

    private ListView lv19;
    private ListView lv20;
    private ListView lv21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_schedule);

        requestQueue = Volley.newRequestQueue(WeeklySchedule.this);
        //  //TextViewMonday = (TextView) findViewById(R.id.textViewMonday);


        mDatabaseHelper = new DatabaseHelper(this);


        toscheduler();


        lv1 = (ListView) findViewById(R.id.listView1);
        lv2 = (ListView) findViewById(R.id.listView2);

        lv4 = (ListView) findViewById(R.id.listView4);
        lv5 = (ListView) findViewById(R.id.listView5);


        lv7 = (ListView) findViewById(R.id.listView7);
        lv8 = (ListView) findViewById(R.id.listView8);


        lv10 = (ListView) findViewById(R.id.listView10);
        lv11 = (ListView) findViewById(R.id.listView11);


        lv13 = (ListView) findViewById(R.id.listView13);
        lv14 = (ListView) findViewById(R.id.listView14);


        lv16 = (ListView) findViewById(R.id.listView16);
        lv17 = (ListView) findViewById(R.id.listView17);


        lv19 = (ListView) findViewById(R.id.listView19);
        lv20 = (ListView) findViewById(R.id.listView20);


    }



    private void toscheduler() {

        Cursor ourdata = mDatabaseHelper.getData();

        dataObject = new JSONObject();
        myobj = new JSONObject();

        int i = 1;


        while (ourdata.moveToNext()) {

            try {
                dataObject.put("appliance_name", ourdata.getString(1));

                dataObject.put("power", ourdata.getDouble(5));
                dataObject.put("appliance_type", ourdata.getDouble(2));
                dataObject.put("usage_weekly", ourdata.getInt(4));
                dataObject.put("total_time", ourdata.getDouble(3));
                dataObject.put("lower_limit", ourdata.getString(6)+":"+ourdata.getString(7));
                dataObject.put("upper_limit", ourdata.getString(8)+":"+ourdata.getString(9));

                myobj.put("sam" + i, dataObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            dataObject = new JSONObject();
            i++;
        }
        // Message.message(getApplicationContext(), obj.toString());
//********************************************************************************
        //  My Code
//********************************************************************************

        LoginUser backgroundTask = new LoginUser(this);
        backgroundTask.execute();


        //********************************************************************************
        //  My Code
//********************************************************************************


        // getschedule();
    }

    public class LoginUser extends AsyncTask<String, Void, String> {
        Context ctx;

        LoginUser(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            loginUser();

            return null;
        }

        private void loginUser() {


            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, myobj,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            Toast toast1,toast2;
                            Context context1 = getApplicationContext();
                            int duration = Toast.LENGTH_LONG;


                            toast1 = Toast.makeText(context1, myobj.toString(), duration);
                            toast1.show();


                            toast1 = Toast.makeText(context1, response.toString(), duration);
                            toast1.show();



                            try {

                                for (int i = 0; i <response.length(); i++) {
                                    JSONObject obj = response.getJSONObject(i);
                                    Iterator<String> keys = obj.keys();
                                    String day = obj.getString("day");
                                    if (day.equals("Monday")){

                                        //while (keys.hasNext()) {
                                        // String key = keys.next();

                                        DeviceNamesMon.add(obj.getString("appliance_name"));
                                        TimeMon.add(obj.getString("time"));
                                        //}

                                    }else if (day.equals("Tuesday")){
                                        DeviceNamesTue.add(obj.getString("appliance_name"));
                                        TimeTue.add(obj.getString("time"));
                                    }
                                    else if(day.equals("Wednesday")){
                                        DeviceNamesWed.add(obj.getString("appliance_name"));
                                        TimeWed.add(obj.getString("time"));

                                    }
                                    else if(day.equals("Thursday")){
                                        DeviceNamesThur.add(obj.getString("appliance_name"));
                                        TimeThur.add(obj.getString("time"));
                                    }
                                    else if (day.equals("Friday")){
                                        DeviceNamesFri.add(obj.getString("appliance_name"));
                                        TimeFri.add(obj.getString("time"));
                                    }else if (day.equals("Saturday")){
                                        DeviceNamesSat.add(obj.getString("appliance_name"));
                                        TimeSat.add(obj.getString("time"));
                                    }else if (day.equals("Sunday")){
                                        DeviceNamesSun.add(obj.getString("appliance_name"));
                                        TimeSun.add(obj.getString("time"));
                                    }
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            lv1.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, DeviceNamesMon));
                            lv2.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, TimeMon));

                            lv4.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, DeviceNamesTue));
                            lv5.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, TimeTue));

                            lv7.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, DeviceNamesWed));
                            lv8.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, TimeWed));

                            lv10.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, DeviceNamesThur));
                            lv11.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, TimeThur));

                            lv13.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, DeviceNamesFri));
                            lv14.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, TimeFri));

                            lv16.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, DeviceNamesSat));
                            lv17.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, TimeSat));

                            lv19.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, DeviceNamesSun));
                            lv20.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, TimeSun));



                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast;
                    Context context1 = getApplicationContext();
                    int duration = Toast.LENGTH_LONG;
                    toast = Toast.makeText(context1, error.toString(), duration);
                    toast.show();
                }
            });





            requestQueue.add(jsonArrayRequest);

        }   // end of loginuser function
    }   // End of LoginUser Class





    public void backtodashboard(View view) {
        opendash();
    }
    private void opendash(){
        // Intent i = new Intent(this, DashBoardActivity.class);
        //startActivity(i);

    }
}

package com.eveati.sajid.teslar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eveati.sajid.teslar.Model.ApiValues_Model;
import com.eveati.sajid.teslar.Model.GetPowerMonth_Model;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LineChartActivity extends AppCompatActivity {

    private static final String TAG = "LineChartActivity";
    private LineChart mChart;
    ArrayList<ApiValues_Model> list;
    ArrayList<GetPowerMonth_Model> daily;
    ArrayList<Entry> graph_values;
    ImageView imgBack,imgOption;
    String baseurl="http://18.222.102.183/getuser/",url;
    SharedPreferences sharedPreferences;
    int user_id_local;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        sharedPreferences= getSharedPreferences("MyData", Context.MODE_PRIVATE);

        user_id_local=sharedPreferences.getInt("USERIDLOCAL",0);
        url=baseurl+user_id_local;


        mChart = (LineChart) findViewById(R.id.lineChart);
        imgBack=(ImageView)findViewById(R.id.img_back);
        imgOption=(ImageView)findViewById(R.id.img_option);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LineChartActivity.this,DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        imgOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(LineChartActivity.this,v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id==R.id.idjan){
                            init_showdata(2018,1);


                        }else if (id==R.id.idfeb){
                            init_showdata(2018,2);

                        }else if (id==R.id.idmar){
                            init_showdata(2018,3);

                        }else if (id==R.id.idapr){
                            init_showdata(2018,4);

                        }else if (id==R.id.idmay){
                            init_showdata(2018,5);

                        }else if (id==R.id.idjun){
                            init_showdata(2018,6);

                        }else if (id==R.id.idjul){
                            init_showdata(2018,7);

                        }else if (id==R.id.idaug){
                            init_showdata(2018,8);

                        }else if (id==R.id.idsep){
                            init_showdata(2018,9);

                        }else if (id==R.id.idoct){
                            init_showdata(2018,10);

                        }else if (id==R.id.idnov){
                            init_showdata(2018,11);

                        }else if (id==R.id.iddec){
                            init_showdata(2018,12);

                        }
                        return true;
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.months, popup.getMenu());
                popup.show();
            }
        });

        init_showdata(2018,7);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        mChart.getDescription().setEnabled(false);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        String[] values = new String[] {"1", "2", "3", "4", "5", "6" , "7", "8", "9", "10", "11", "12","13", "14", "15", "16", "17", "18" , "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new MyAxisValueFormatter(values));


    }

    public class MyAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues;

        public MyAxisValueFormatter(String[] values) {
            this.mValues = values;
        }


        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.months, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.idjan){
            init_showdata(2018,1);


        }else if (id==R.id.idfeb){
            init_showdata(2018,2);

        }else if (id==R.id.idmar){
            init_showdata(2018,3);

        }else if (id==R.id.idapr){
            init_showdata(2018,4);

        }else if (id==R.id.idmay){
            init_showdata(2018,5);

        }else if (id==R.id.idjun){
            init_showdata(2018,6);

        }else if (id==R.id.idjul){
            init_showdata(2018,7);

        }else if (id==R.id.idaug){
            init_showdata(2018,8);

        }else if (id==R.id.idsep){
            init_showdata(2018,9);

        }else if (id==R.id.idoct){
            init_showdata(2018,10);

        }else if (id==R.id.idnov){
            init_showdata(2018,11);

        }else if (id==R.id.iddec){
            init_showdata(2018,12);

        }
        return true;
    }
    public void backtodashboard(View view) {
        opendash();
    }
    public void onClick(View view){
        openmonthlycharts();
    }
    private void openmonthlycharts(){
        Intent i = new Intent(this, LineChartMonthly.class);
        startActivity(i);
    }
    private void opendash(){
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    public void init_showdata(final int year,final int month){
        list=new ArrayList<ApiValues_Model>();
        daily=new ArrayList<GetPowerMonth_Model>();
        graph_values=new ArrayList<Entry>();

        //final String REGISTER_URL = "http://18.222.102.183/all-collected-data";
        //final String REGISTER_URL="http://18.222.102.183/all-data";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
//                            final JSONObject item=new JSONObject(response);
                            final JSONArray jsonArray=new JSONArray(response);
//                            JSONArray users = item.getJSONArray("");
//                            final String message = item.getString("message");
                            if(jsonArray.length()!=0) {
                                for (int i = 0; i <  jsonArray.length(); i++) {
                                    JSONObject user = jsonArray.getJSONObject(i);
                                    list.add(new ApiValues_Model(
                                            user.getString("system_id"),
                                            user.getString("date"),
                                            user.getInt("timespanid"),
                                            user.getInt("total_power_ac"),
                                            user.getInt("total_power_dc"),
                                            user.getInt("voltage_ac"),
                                            user.getInt("current_ac"),
                                            user.getInt("voltage_dc"),
                                            user.getInt("current_dc"),
                                            0,
                                            0

                                    ));

                                    try {
                                        get_day(user.getString("date"),user.getInt("total_power_ac"));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                                for (int i=1;i<=30;i++) {
                                    int sum = 0;
                                    for (int b=0;b<daily.size();b++){
                                        if (i == daily.get(b).getDate()&&year==daily.get(b).getYear()&&month==daily.get(b).getMonth()) {

                                            sum+=daily.get(b).getPower();



                                        }

                                    }
                                    graph_values.add(new Entry(i-1,sum));
                                    Log.d("cccc", sum + "  " + i);

                                }

                                mChart.clear();

                                LineDataSet set2 = new LineDataSet(graph_values, "Power");
                                set2.setFillAlpha(110);
                                set2.setColor(Color.RED);
                                set2.setLineWidth(2f);
                                set2.setDrawValues(false);
                                set2.setValueTextSize(8f);
                                set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                                set2.setCubicIntensity(0.2f);
                                ArrayList<ILineDataSet> dataSet1 = new ArrayList<>();
                                dataSet1.add(set2);
                                LineData data1 = new LineData(dataSet1);
                                mChart.setData(data1);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                10000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void get_day(String d,int power) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(d);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        int date = calendar.get(Calendar.DATE);
//+1 Is Important Because if the month is January then coming 0 so Add +1
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);

        Log.d("datee","Date: "+date +"Month: "+ month + "Year: "+year);

        daily.add(new GetPowerMonth_Model(month,power,year,date,0));
    }


}

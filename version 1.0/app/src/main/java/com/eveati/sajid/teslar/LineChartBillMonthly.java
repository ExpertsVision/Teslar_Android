package com.eveati.sajid.teslar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LineChartBillMonthly extends AppCompatActivity {
    private static final String TAG = "LineChartMonthly";
    private LineChart mChart;
    ArrayList<ApiValues_Model> list;
    ArrayList<GetPowerMonth_Model> monthly;
    ArrayList<Entry> graph_values;
    ImageView imgBack,imgOption;

    SharedPreferences sharedPreferences;
    int user_id_local;
    String baseurl="http://3.16.13.121/getuser/",url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart_bill_monthly);


        sharedPreferences= getSharedPreferences("MyData", Context.MODE_PRIVATE);

        user_id_local=sharedPreferences.getInt("USERIDLOCAL",0);
        url=baseurl+user_id_local;

        mChart = (LineChart) findViewById(R.id.lineChart);
        imgBack=(ImageView)findViewById(R.id.img_back);
        imgOption=(ImageView)findViewById(R.id.img_option);
        //spinner = (Spinner) findViewById(R.id.spinner);

        //mChart.setOnChartGestureListener(LineChartActivity.this);
        //mChart.setOnChartValueSelectedListener(LineChartActivity.this);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LineChartBillMonthly.this,DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        imgOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(LineChartBillMonthly.this,v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id==R.id.id2013){
                            mChart.clear();
                            init_showdata(2013);

                        }
                        else if(id==R.id.id2014){
                            mChart.clear();
                            init_showdata(2014);

                        }else if(id==R.id.id2015){
                            mChart.clear();
                            init_showdata(2015);

                        }else if(id==R.id.id2016){
                            mChart.clear();
                            init_showdata(2016);

                        }else if(id==R.id.id2017){
                            mChart.clear();
                            init_showdata(2017);

                        }else if (id==R.id.id2018){
                            mChart.clear();
                            init_showdata(2018);

                        }
                        return true;
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.main, popup.getMenu());
                popup.show();
            }
        });

        init_showdata(2018);

        mChart.setDragEnabled(true);
        mChart.setTouchEnabled(false);
        mChart.setScaleEnabled(true);
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);
        mChart.setMaxHighlightDistance(200);
        mChart.setScaleEnabled(false);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        String[] values = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun" , "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        XAxis xAxis  = mChart.getXAxis();
        xAxis.setValueFormatter(new LineChartBillMonthly.MyAxisValueFormatter(values));


    }

    public class MyAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues;
        public MyAxisValueFormatter(String[] values){
            this.mValues = values;
        }


        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }

    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==R.id.id2013){
            mChart.clear();
            init_showdata(2013);

        }
        else if(id==R.id.id2014){
            mChart.clear();
            init_showdata(2014);

        }else if(id==R.id.id2015){
            mChart.clear();
            init_showdata(2015);

        }else if(id==R.id.id2016){
            mChart.clear();
            init_showdata(2016);

        }else if(id==R.id.id2017){
            mChart.clear();
            init_showdata(2017);

        }else if (id==R.id.id2018){
            mChart.clear();
         init_showdata(2018);

        }
        return true;
    }
    public void onClick(View view){
        openmonthlycharts();
    }
    private void openmonthlycharts(){
        Intent i = new Intent(this, LineChartBill.class);
        startActivity(i);
    }
    public void backtodashboard(View view) {
        opendash();
    }
    private void opendash(){
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
    public void init_showdata(final int year){
        list=new ArrayList<ApiValues_Model>();
        monthly=new ArrayList<GetPowerMonth_Model>();
        graph_values=new ArrayList<Entry>();

       // final String REGISTER_URL = "http://18.222.102.183/all-collected-data";
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
                                        get_day(user.getString("date"),user.getInt("total_power_ac"),user.getInt("timespanid"));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                                for (int i=1;i<=12;i++) {
                                    int sum = 0;
                                    int bill=0;
                                    for (int b=0;b<monthly.size();b++){
                                        if (i == monthly.get(b).getMonth()&&year==monthly.get(b).getYear()) {
                                            if(monthly.get(b).getTime_spand()==14||monthly.get(b).getTime_spand()==15||monthly.get(b).getTime_spand()==16||monthly.get(b).getTime_spand()==17) {
                                                sum += monthly.get(b).getPower();
                                                bill=(sum/1000)*4;

//
                                            }else {
                                                sum += monthly.get(b).getPower();
                                                bill=(sum/1000)*2;
                                            }







                                        }

                                    }
                                    graph_values.add(new Entry(i-1,bill));
                                    //Log.d("cccc", sum + "  " + i);

                                }

                                mChart.clear();

                                LineDataSet set2 = new LineDataSet(graph_values, "Bill");
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


    public void get_day(String d,int power,int time_id) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(d);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        int date = calendar.get(Calendar.DATE);
//+1 Is Important Because if the month is January then coming 0 so Add +1
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);

        //Log.d("datee","Date: "+date +"Month: "+ month + "Year: "+year);

        monthly.add(new GetPowerMonth_Model(month,power,year,date,time_id));
    }

}


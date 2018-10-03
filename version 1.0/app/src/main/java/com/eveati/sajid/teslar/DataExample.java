package com.eveati.sajid.teslar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;



public class DataExample extends AppCompatActivity {
    private static final String TAG = "DataExample";
    private LineChart mChart;
    //ArrayList<Entry> xValues;
    private ListView lv;

    //ArrayList<Entry> x;
    //ArrayList<String> y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_example);
        //x = new ArrayList<Entry>();
        //y = new ArrayList<String>();

        mChart = (LineChart) findViewById(R.id.lineChart);
        mChart.setDrawGridBackground(false);
        //mChart.setDescription("");
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        lv = (ListView) findViewById(R.id.listViewHeroes);
        //x = new ArrayList<Entry>();
        //y = new ArrayList<String>();
        //mChart = (LineChart) findViewById(R.id.lineChart);
        //getHeroes();

        //mChart.setOnChartGestureListener(LineChartActivity.this);
        //mChart.setOnChartValueSelectedListener(LineChartActivity.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        mChart.setTouchEnabled(true);
        mChart.setPinchZoom(true);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //xValues = new ArrayList<>();

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 50f));
        yValues.add(new Entry(2, 70f));
        yValues.add(new Entry(3, 30f));
        yValues.add(new Entry(4, 50f));



        LineDataSet set1 = new LineDataSet(yValues, "Data Set 1");
        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(15f);

        ArrayList<ILineDataSet> dataSet = new ArrayList<>();
        dataSet.add(set1);

        LineData data = new LineData(dataSet);
        mChart.setData(data);
        //String[] values = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun"};
        getdata();
        //XAxis xAxis  = mChart.getXAxis();
        //xAxis.setValueFormatter(new MyAxisValueFormatter(values));
        //String[] values = new String[] {"1", "2", "3", "4", "5", "6" };
        //XAxis xAxis  = mChart.getXAxis();
        //xAxis.setValueFormatter(new MyAxisValueFormatter(values));

    }

    private void getdata() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<List<Data>> call = api.getdata();
        call.enqueue(new Callback<List<Data>>(){
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {

                List<Data> heroList = response.body();

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getName();
                }
                XAxis xAxis  = mChart.getXAxis();
                xAxis.setValueFormatter(new MyAxisValueFormatter(heroes));

                //displaying the string array into listview
                //lv.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, xAxis.toString()));


            }


    @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

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
}





package com.eveati.sajid.teslar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PowerGraph extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.power_graph);



    }







    @Override
    public void onClick(View v) {

    open();

    }
    private void open(){
        Intent i = new Intent(this, LineChartActivity.class);
        startActivity(i);

    }






}

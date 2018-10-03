package com.eveati.sajid.teslar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainInfoActivity extends Activity {
    private Button ButtonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_info);

        ButtonNext = (Button) findViewById(R.id.buttonNext);
    }
    public void sendd(View view){
        open();
    }
    private void open(){
        Intent i = new Intent(this, MapActivity.class);
        startActivity(i);
    }
    public void backtodashboard(View view) {
        opendash();
    }
    private void opendash(){
        Intent i = new Intent(this, DashBoardActivity.class);
        startActivity(i);

    }
}

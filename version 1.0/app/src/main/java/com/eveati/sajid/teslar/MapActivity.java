package com.eveati.sajid.teslar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MapActivity extends Activity {
    private Button ButtonNext;
    RelativeLayout Rl_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);


        ButtonNext = (Button) findViewById(R.id.buttonNext);

    }
    public void sendd(View view){
        openpowergraph();
    }
    private void openpowergraph(){
        Intent i = new Intent(this, DashBoardActivity.class);
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

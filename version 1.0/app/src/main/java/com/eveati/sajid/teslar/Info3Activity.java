package com.eveati.sajid.teslar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Info3Activity extends Activity{
    private Button ButtonNext;
    private Button ButtonSkip;
    SharedPreferences sharedPreferences;
    int login_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info3_activity);
        ButtonNext = (Button) findViewById(R.id.buttonNext);
        ButtonSkip = (Button) findViewById(R.id.buttonSkip);
    }
    public void sendd(View view){
        opennextactivity();
    }
    private void opennextactivity(){
        Intent i = new Intent(this, MainInfoActivity.class);
        startActivity(i);
    }
    public void skip(View view){
        openmainactivity();
    }
    private void openmainactivity(){

        sharedPreferences= getSharedPreferences("MyData", Context.MODE_PRIVATE);

        login_status=sharedPreferences.getInt("STATUS",0);

        if(login_status==3|login_status==1)


        {
            startActivity(new Intent(Info3Activity.this, LoginActivity.class));
            finish();
        }
        else if (login_status==2)
        {
            startActivity(new Intent(Info3Activity.this, DashBoardActivity.class));
            finish();
        }
    }
}

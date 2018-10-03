package com.eveati.sajid.teslar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class CreateAccountActivity extends Activity {
    private Button ButtonNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_account);
     ButtonNext = (Button) findViewById(R.id.buttonNext);

    }
    public void sendd(View view){
        opennextwindow();
    }
    private void opennextwindow(){
        Intent i = new Intent(this, InfoActivity.class);
        startActivity(i);
    }
}

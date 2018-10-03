package com.eveati.sajid.teslar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.eveati.sajid.teslar.Model.DatabaseHelper;
import java.util.ArrayList;
import java.util.Set;

public class SchedulingActivity extends AppCompatActivity {
    EditText EditTextStartingDate,EditTextApplianceName,EditTextTotalTime,EditTextRating;
    Spinner SpinnerApplianceType,SpinnerUsage,SpinnerUL1,SpinnerUL2,SpinnerLL1, SpinnerLL2;
    Button ButtonSave,ButtonView;
    DatabaseHelper mDatabaseHelper;
    private Set<String> set;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);

        imgBack=(ImageView)findViewById(R.id.img_back);
        EditTextStartingDate = (EditText) findViewById(R.id.editTextStartingDate);
        EditTextApplianceName = (EditText) findViewById(R.id.editTextApplianceName);
        EditTextTotalTime = (EditText) findViewById(R.id.editTextTotalTime);
        EditTextRating = (EditText) findViewById(R.id.editTextRating);
        EditTextRating = (EditText) findViewById(R.id.editTextRating);
        SpinnerApplianceType=(Spinner) findViewById(R.id.spinnerApplianceType);
        SpinnerUsage=(Spinner) findViewById(R.id.spinnerUsage);
        SpinnerLL1=(Spinner) findViewById(R.id.spinnerLL1);
        SpinnerLL2=(Spinner) findViewById(R.id.spinnerLL2);
        SpinnerUL1=(Spinner) findViewById(R.id.spinnerUL1);
        SpinnerUL2=(Spinner) findViewById(R.id.spinnerUL2);
        ButtonSave = (Button) findViewById(R.id.buttonSave);
        ButtonView = (Button) findViewById(R.id.buttonView);
        //ButtonSave.setEnabled(!EditText.isEmpty());

        mDatabaseHelper = new DatabaseHelper(this);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchedulingActivity.this,DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EditTextApplianceName.length() != 0) {
                    AddData();
                    EditTextApplianceName.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
               }
            });

        ButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(SchedulingActivity.this, DevicesActivity.class);
            startActivity(intent);
            }
        });
    }
    private void AddData() {
        String NAME=EditTextApplianceName.getText().toString();
       // String STARTINGDATE=EditTextStartingDate.getText().toString();
        String MAXTIME=EditTextTotalTime.getText().toString();
        String USAGE=SpinnerUsage.getSelectedItem().toString();
        String RATING=EditTextRating.getText().toString();
        String APPLIANCETYPE=SpinnerApplianceType.getSelectedItem().toString();
        String LL1=SpinnerLL1.getSelectedItem().toString();
        String LL2=SpinnerLL2.getSelectedItem().toString();
        String UL1=SpinnerUL1.getSelectedItem().toString();
        String UL2=SpinnerUL2.getSelectedItem().toString();

       boolean insertData = mDatabaseHelper.addData(NAME,APPLIANCETYPE,MAXTIME,USAGE,RATING,LL1,LL2,UL1,UL2);
       // boolean insertData = mDatabaseHelper.addData(NAME,USAGE,RATING);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }

    public void backtodashboard(View view) {
        opendash();
    }

    private void opendash(){
        Intent i = new Intent(this, DashBoardActivity.class);
        startActivity(i);
    }
}

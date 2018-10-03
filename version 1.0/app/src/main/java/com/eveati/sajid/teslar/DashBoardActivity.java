package com.eveati.sajid.teslar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import org.json.JSONException;
import org.json.JSONObject;;
import android.net.Uri;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import de.hdodenhof.circleimageview.CircleImageView;

public class DashBoardActivity extends AppCompatActivity {
    ImageView img_cross;
    RelativeLayout img_plus_upload;
    CircleImageView img_user;
    RelativeLayout Rl_power_consumption,Rr_billing,Rl_maintenance,Rl_power_scheduling;
    RelativeLayout Rl_logout;
    static final int SELECT_PICTURE = 100;
    DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    int login_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        img_plus_upload= (RelativeLayout) findViewById(R.id.img_upload_image);
        img_user = (CircleImageView) findViewById(R.id.img_user);
        img_cross=(ImageView)findViewById(R.id.cross);
        TextView tvName=(TextView)findViewById(R.id.tv_username);
        dbHelper = new DBHelper(this);
        Rl_logout=(RelativeLayout)findViewById(R.id.Rl_logout);

        if (SPUser.getIntValue(DashBoardActivity.this,SPUser.USER_ID)==0){
            tvName.setVisibility(View.GONE);
            img_user.setVisibility(View.GONE);
            img_plus_upload.setVisibility(View.GONE);
            Rl_logout.setVisibility(View.GONE);

        }
        else {
            tvName.setText(SPUser.getValue(DashBoardActivity.this,SPUser.USERNAME));
            loadImageFromDB();
        }

        Idgetting();
        Clickevent();

        Rl_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login_status=3;
                Intent i= new Intent(DashBoardActivity.this,LoginActivity.class);

                sharedPreferences= getSharedPreferences("MyData", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("STATUS", login_status);
                editor.apply();








                SPUser.setIntValue(DashBoardActivity.this,SPUser.USER_ID,0);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i= new Intent(DashBoardActivity.this,.class);
                startActivity(i);*/
            }
        });
        img_plus_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        JSONObject json = new JSONObject();
        JSONObject item = new JSONObject();

        try {
            item.put("information", "test");
            item.put("id", 3);
            item.put("name", "course1");
            json.put("course", item);

            Log.d("Json_arrray",json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }
    private void Idgetting() {
        Rl_maintenance=(RelativeLayout)findViewById(R.id.Rl_maintenence);
        Rl_power_consumption=(RelativeLayout)findViewById(R.id.Rl_power_consumption);
        Rl_power_scheduling=(RelativeLayout)findViewById(R.id.Rl_power_scheduling);
        Rr_billing=(RelativeLayout)findViewById(R.id.Rl_billing);
    }
    private void Clickevent() {
        Rl_maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DashBoardActivity.this,MaintenanceReqestActivity.class);
                startActivity(i);

            }
        });
        Rl_power_scheduling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DashBoardActivity.this,SchedulingActivity.class);
                startActivity(i);
            }
        });
        Rl_power_consumption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DashBoardActivity.this,LineChartMonthly.class);
                startActivity(i);
            }
        });

        Rr_billing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DashBoardActivity.this,LineChartBillMonthly.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {

                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {

                    // Saving to Database...
                    if (saveImageInDB(selectedImageUri)) {
                        img_user.setImageURI(selectedImageUri);
                    }

                    // Reading from Database after 3 seconds just to show the message
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (loadImageFromDB()) {
                            loadImageFromDB();
                            }
                        }
                    }, 3000);
                }

            }
        }
    }
    Boolean loadImageFromDB() {
        try {
            dbHelper.open();
            byte[] bytes = dbHelper.retreiveImageFromDB();
            dbHelper.close();
            // Show Image from DB in ImageView
            img_user.setImageBitmap(Utils.getImage(bytes));
            return true;
        } catch (Exception e) {

            dbHelper.close();
            return false;
        }
    }
    Boolean saveImageInDB(Uri selectedImageUri) {

        try {
            dbHelper.open();
            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
            dbHelper.insertImage(inputData);
            dbHelper.close();
            return true;
        } catch (IOException ioe) {

            dbHelper.close();
            return false;
        }

    }
}

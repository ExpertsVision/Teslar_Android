package com.eveati.sajid.teslar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.eveati.sajid.teslar.Model.DatabaseHelper;
public class MaintenanceReqestActivity extends AppCompatActivity {
    Button ButtonSubmit,ButtonView;
    String url="http://expertsvision.site/api/auth/complaint",preferreddate;
    private RequestQueue requestQueue;
    DatabaseHelper mDatabaseHelper;
    Cursor ourdata;
    int  userid;
    EditText EditTextTitle, EditTextDescription;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_reqest);

        imgBack=(ImageView)findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MaintenanceReqestActivity.this,DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        requestQueue = Volley.newRequestQueue(this);
        mDatabaseHelper = new DatabaseHelper(this);


        ButtonSubmit = (Button) findViewById(R.id.buttonSubmit);
        ButtonView = (Button) findViewById(R.id.buttonViewRequests);


        EditTextTitle= (EditText) this.findViewById(R.id.editTextTitle) ;
        EditTextDescription= (EditText) this.findViewById(R.id.editTextDescription) ;

        //Calendar c = Calendar.getInstance();
        SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        preferreddate= ss.format(date);

        //TextViewHeading=(TextView) this.findViewById(R.id.textViewHeading);
        // TextViewHeading.setText(currentdate);

        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitComplaint();
            }
        });

        ButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                goHome();
            }
        });
    }

    private void goHome(){
        Intent i = new Intent(this, MaintenanceModuleActivity.class);
        startActivity(i);

    }

    private void submitComplaint() {


        LoginUser backgroundTask = new LoginUser(this);
        backgroundTask.execute();
        Intent i = new Intent(this, MaintenanceModuleActivity.class);
        startActivity(i);
    }

    public class LoginUser extends AsyncTask<String, Void, String>
    {

        Context ctx;

        LoginUser(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... strings) {
            loginUser();
            return null;
        }
    }

    private void loginUser() {

        ourdata = mDatabaseHelper.getUserData();

        while(ourdata.moveToNext()) {

            userid = ourdata.getInt(0);

        }


        /*Toast toast,toast1;
        Context context1 = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        toast = Toast.makeText(context1, userid, duration);
        toast.show();*/

        //userid=11;

        String title=EditTextTitle.getText().toString();
        String description=EditTextDescription.getText().toString();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("Title", title);
        params.put("Description", description);
        params.put("Submission_Date", preferreddate);
        params.put("Consumer_ID", userid);


        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast toast,toast1;
                        Context context1 = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        toast = Toast.makeText(context1, response.toString(), duration);
                        toast.show();




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast,toast1;
                Context context1 = getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                toast = Toast.makeText(context1, error.toString(), duration);
                toast.show();



            }
        });
        requestQueue.add(jsonRequest);







    }
}

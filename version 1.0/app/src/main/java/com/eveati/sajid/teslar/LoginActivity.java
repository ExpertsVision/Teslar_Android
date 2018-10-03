package com.eveati.sajid.teslar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import com.eveati.sajid.teslar.Model.DatabaseHelper;







public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView ButtonLogIn;
    public ArrayList<String> loginResponseMsg;

    EditText editTextEmail, editTextPassword;
    TextView buttonForgotPassword;
    Button buttonLogin, buttonRegister;


    private ProgressDialog pDialog, pDialogGettingDetail;
    private RequestQueue mRequestQueue;
    //private StringRequest postRequest;
    private RequestQueue requestQueue;

    int uid,user_id_local,login_status;

    String username,email,password;

     DatabaseHelper mDatabaseHelper;
    SharedPreferences sharedPreferences;

    //private String url = "18.222.102.183:9000/get_product";
    //private String url= "http://writinggems.com/api/auth/register";
    private JSONObject parameters;
    //private String url="http://18.216.115.254/login";
    private String url="http://writinggems.com/api/auth/login";
    //String MY_PREFS_NAME;
    TextView tvSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);



        loginResponseMsg = new ArrayList<>();
        loginResponseMsg.add(Constants.LOGIN_MSG_SUCCESS);
        loginResponseMsg.add(Constants.LOGIN_MSG_FAILED);
        loginResponseMsg.add(Constants.LOGIN_MSG_FAILED_CONNECT);
        tvSkip=(TextView)findViewById(R.id.tv_skip);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);



        requestQueue = Volley.newRequestQueue(LoginActivity.this);

        sharedPreferences= getSharedPreferences("MyData",Context.MODE_PRIVATE);
        mDatabaseHelper = new DatabaseHelper(this);

        init_components();
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUser.setIntValue(LoginActivity.this, SPUser.USER_ID, 0);
                Intent i = new Intent(LoginActivity.this, InfoActivity.class);
                startActivity(i);
            }
        });


    }

    private void init_components() {


       // editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        //editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        ButtonLogIn = (TextView) findViewById(R.id.buttonLogIn);
        ButtonLogIn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonLogIn:
                login_status = 3;

                SharedPreferences.Editor editor= sharedPreferences.edit();

                editor.putInt("STATUS",login_status);

                editor.apply();

                    validateLoginForm();
                break;
            case R.id.buttonNext:

                Intent i = new Intent(this, InfoActivity.class);
                startActivity(i);

                break;

        }

    }


    private void validateLoginForm() {

        //***********************************************************************

        if (TextUtils.isEmpty(editTextEmail.getText().toString())) {
            editTextEmail.setError("Please Enter Your Email");
            return;
        }
        if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
            editTextPassword.setError("Please Enter Your Password");
            return;
        }
        LoginUser backgroundTask = new LoginUser(this);
        backgroundTask.execute();








    }




    public class LoginUser extends AsyncTask<String, Void, String> {
        Context ctx;

        LoginUser(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

                        loginUser();

            return null;
        }

        private void loginUser() {

            email = editTextEmail.getText().toString();
            password = editTextPassword.getText().toString();



            JSONObject json = new JSONObject();
            try {

                json.put("email", email);
                json.put("password", password);



            } catch (JSONException e) {
                e.printStackTrace();
            }


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                            Toast toast;
                            Context context1 = getApplicationContext();
                            int duration = Toast.LENGTH_LONG;


                           try {
                                uid = response.getInt("User_ID");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                username=response.getString("User_Name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                           boolean insertData = mDatabaseHelper.addUserData(uid,username,email,password);
                           // Log.v("gfghfj",username+uid);
                         // boolean insertData = mDatabaseHelper.addData(NAME,USAGE,RATING);

                           //sajid Log.v("derfewarfwe",insertData+"");
                           /* if (insertData) {
                                Toast toast1;
                                Context context11 = getApplicationContext();
                                toast = Toast.makeText(context1, "Successfully inserted", duration);
                                toast.show();
                            }
                            else
                                {
                                Toast toast1;
                                Context context11 = getApplicationContext();

                                toast = Toast.makeText(context1, "something went wrong", duration);
                                toast.show();
                            }*/


                            user_id_local=sharedPreferences.getInt("USERIDLOCAL",0);
                            if (user_id_local==0|user_id_local==uid) {

                                login_status = 2;   // it means user is logged in

                                SharedPreferences.Editor editor= sharedPreferences.edit();

                                editor.putInt("USERIDSOURCE",uid);
                                editor.putString("USERNAME",username);
                                editor.putInt("STATUS",login_status);
                                editor.putInt("USERIDLOCAL",uid);
                                editor.apply();

                                Intent i = new Intent(LoginActivity.this, InfoActivity.class);
                                startActivity(i);

                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Sorry You can not login..",Toast.LENGTH_LONG).show();
                            }







                                   SPUser.setValue(LoginActivity.this, SPUser.USERNAME, username);
                              SPUser.setIntValue(LoginActivity.this, SPUser.USER_ID, uid);
                             SPUser.setIntValue(LoginActivity.this, SPUser.USER_SOURCE, uid);


                            /*}else {
                                Toast.makeText(LoginActivity.this,"Sorry You can not login..",Toast.LENGTH_LONG).show();
                            }*/



                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast;
                    Context context1 = getApplicationContext();
                    int duration = Toast.LENGTH_LONG;
                    toast = Toast.makeText(context1, error.toString(), duration);
                    toast.show();
                }
            });

            requestQueue.add(jsonObjectRequest);
           //requestQueue.add(bigobj);

            //requestQueue.add(jsonObjectRequest);

        }   // end of loginuser function
    }   // End of LoginUser Class

    //***********************************************************************
}

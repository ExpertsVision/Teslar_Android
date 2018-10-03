package com.eveati.sajid.teslar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends Activity {
    private TextView TextView;
    private ImageView ImageView;
    private static int SPLASH_TIME_OUT = 2000;
    SharedPreferences sharedPreferences ;
    //SharedPreferences.Editor prefEdit;
    int login_status,user_id_local,user_id_server;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        user_id_local=sharedPreferences.getInt("USERIDLOCAL",0);


        ImageView = (ImageView) findViewById(R.id.imageView);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        if (user_id_local == 0 )   //  No userid is saved because no user has signed in before
        {
            login_status = 1;   // 1 means now anyone can login using correct credentials.



        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("USERIDSOURCE", 0);
        editor.putString("USERNAME", "");
        editor.putInt("STATUS", login_status);
        editor.putInt("USERIDLOCAL", 0);
        editor.apply();
    }



        ImageView.startAnimation(myanim);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                sharedPreferences= getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();



                login_status=sharedPreferences.getInt("LOGINSTATUS",0);





                if (login_status==0|login_status==1|login_status==3) {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
                 else if (login_status==2)
                {
                    Intent i = new Intent(SplashActivity.this, DashBoardActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        }, SPLASH_TIME_OUT);





        /*
        final Intent i = new Intent(this,LoginActivity.class);
        Thread timer = new Thread() {
            public void run () {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }

            }

        };
        timer.start();*/
    }
}

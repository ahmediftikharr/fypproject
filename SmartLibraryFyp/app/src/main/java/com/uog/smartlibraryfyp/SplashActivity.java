package com.uog.smartlibraryfyp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash();
    }
    private void splash()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences pref = getSharedPreferences("smart_library", MODE_PRIVATE);
                String remember=pref.getString("remember","no");
                Intent mIntent;
                if(remember.equals("yes"))
                {
                    mIntent = new Intent(SplashActivity.this, MenuActivity.class);
                }
                else
                {
                    mIntent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(mIntent);
                finish();
            }
        }, 3000);
    }
}

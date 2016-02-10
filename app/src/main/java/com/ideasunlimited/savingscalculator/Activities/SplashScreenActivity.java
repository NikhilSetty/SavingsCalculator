package com.ideasunlimited.savingscalculator.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ideasunlimited.savingscalculator.Constants;
import com.ideasunlimited.savingscalculator.R;

/**
 * Created by Kbhargav on 2/9/2016.
 */
public class SplashScreenActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable()
        {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, Constants.SplashScreenWaitTime);
    }
}

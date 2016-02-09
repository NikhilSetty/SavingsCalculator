package com.ideasunlimited.savingscalculator.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

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

       Thread timer = new Thread()
       {
           @Override
           public void run()
           {
               try
               {
                   sleep(Constants.SplashScreenWaitTime);
               }
               catch (InterruptedException ie)
               {
                   ie.printStackTrace();
               }
               finally
               {
                   Intent intent_mainActivity = new Intent(SplashScreenActivity.this,MainActivity.class);
                   startActivity(intent_mainActivity);
                   finish();
               }
           }
       };

        timer.start();
    }
}

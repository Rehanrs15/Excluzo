package com.examples.rehan.excluzo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.examples.rehan.excluzo.Preferences.LoginPreferences;
import com.examples.rehan.excluzo.R;

public class SplashActivity extends BaseActivity {

    LoginPreferences loginPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loginPreferences = new LoginPreferences(SplashActivity.this);
        applyFont(SplashActivity.this,findViewById(R.id.base_layout));

        CountDownTimer countDownTimer = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                    Log.e("Logged in as",String.valueOf(loginPreferences.isLoggedIn()));
                    if(loginPreferences.isLoggedIn()) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    else{
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
            }
        }.start();
    }
}

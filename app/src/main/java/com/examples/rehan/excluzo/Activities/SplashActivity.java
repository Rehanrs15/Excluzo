package com.examples.rehan.excluzo.Activities;

import android.os.Bundle;

import com.examples.rehan.excluzo.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        applyFont(SplashActivity.this,findViewById(R.id.base_layout));
    }
}

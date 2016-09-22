package com.examples.rehan.excluzo.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.examples.rehan.excluzo.R;

public class CategoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        applyFont(CategoryActivity.this,findViewById(R.id.base_layout));
    }
}

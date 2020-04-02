package com.bione.corona.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;


import com.bione.corona.R;
import com.bione.corona.ui.base.BaseActivity;


public class ResultActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String type = "";

        if (extras != null) {
            type = extras.getString("type");
            // and get whatever type user account id is
        }

        if (type.equals("1")) {
            setContentView(R.layout.activity_result_one);
        } else if (type.equals("2")) {
            setContentView(R.layout.activity_result_two);
        } else {
            setContentView(R.layout.activity_result_three);
        }

    }
}

package com.bione.corona.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;


import com.bione.corona.R;
import com.bione.corona.ui.base.BaseActivity;


public class ResultActivity extends BaseActivity {

    private String url = "https://www.bione.in";
    private AppCompatTextView tvTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String type = "";
        String title = "";


        if (extras != null) {
            type = extras.getString("type");
            title = extras.getString("title");

            // and get whatever type user account id is
        }
        if (type.equals("1")) {
            setContentView(R.layout.activity_result_one);
        } else if (type.equals("2")) {
            setContentView(R.layout.activity_result_two);
        } else {
            setContentView(R.layout.activity_result_three);
        }

        tvTitle = findViewById(R.id.tvTitle);

        tvTitle.setText(title);


    }

    public void openWebsite(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}

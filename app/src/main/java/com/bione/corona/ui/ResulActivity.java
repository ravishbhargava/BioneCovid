package com.bione.corona.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bione.corona.R;

import static com.bione.corona.ui.SurveyActivity.resultCorona;

public class ResulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_one);

        resultCorona = 0;
    }
}

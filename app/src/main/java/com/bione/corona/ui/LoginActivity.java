package com.bione.corona.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.bione.corona.R;
import com.bione.corona.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    private AppCompatTextView tvContinue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvContinue = findViewById(R.id.tvContinue);

        tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                startActivity(intent);
            }
        });
    }
}

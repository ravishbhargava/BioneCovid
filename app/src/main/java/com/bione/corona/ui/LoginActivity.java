package com.bione.corona.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bione.corona.R;
import com.bione.corona.model.CommonResponse;
import com.bione.corona.network.ApiError;
import com.bione.corona.network.CommonParams;
import com.bione.corona.network.ResponseResolver;
import com.bione.corona.network.RestClient;
import com.bione.corona.ui.base.BaseActivity;
import com.bione.corona.utils.ValidationUtil;

public class LoginActivity extends BaseActivity {

    private AppCompatTextView tvContinue;
    private AppCompatImageView ivBack;
    private AppCompatEditText etEmail;
    private AppCompatEditText etPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvContinue = findViewById(R.id.tvContinue);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidationUtil.checkEmail(etEmail.getText().toString())
                        && ValidationUtil.checkPassword(etPassword.getText().toString())) {
                    callApi();
                }

            }
        });
    }

    private void callApi() {
        final CommonParams commonParams = new CommonParams.Builder()
                .add("email", etEmail.getText().toString())
                .add("password", etPassword.getText().toString())
                .build();

        RestClient.getApiInterface().signin(commonParams.getMap()).enqueue(new ResponseResolver<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse commonResponse) {
                Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(ApiError error) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}

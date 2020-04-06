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
import com.bione.corona.model.onboard.SignUpDatum;
import com.bione.corona.model.onboard.Software;
import com.bione.corona.network.ApiError;
import com.bione.corona.network.CommonParams;
import com.bione.corona.network.ResponseResolver;
import com.bione.corona.network.RestClient;
import com.bione.corona.ui.base.BaseActivity;
import com.bione.corona.utils.CommonData;
import com.bione.corona.utils.ValidationUtil;

import java.util.List;

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

        ivBack.setOnClickListener(view -> finish());

        tvContinue.setOnClickListener(view -> checkValidation());
    }

    private void checkValidation() {
        if (ValidationUtil.checkEmail(etEmail.getText().toString())) {
            if (ValidationUtil.checkPassword(etPassword.getText().toString())) {
                callApi();
            } else {
                showErrorMessage(R.string.error_password);
            }
        } else {
            showErrorMessage(R.string.error_email);
        }
    }

    private void callApi() {
        showLoading();
        final CommonParams commonParams = new CommonParams.Builder()
                .add("email", etEmail.getText().toString())
                .add("password", etPassword.getText().toString())
                .build();

        RestClient.getApiInterface().signin(commonParams.getMap()).enqueue(new ResponseResolver<List<SignUpDatum>>() {
            @Override
            public void onSuccess(List<SignUpDatum> commonResponse) {
                hideLoading();
                if (commonResponse.get(0).getCode().equals("200")) {
                    CommonData.saveEmail(etEmail.getText().toString());
                    CommonData.savePasswordl(etPassword.getText().toString());
                    Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                    startActivity(intent);
                    finishAffinity();
                } else {
                    showErrorMessage(commonResponse.get(0).getMessage());
                }

            }

            @Override
            public void onError(ApiError error) {
                hideLoading();
                showErrorMessage(error.getMessage());
            }

            @Override
            public void onFailure(Throwable throwable) {
                hideLoading();
                showErrorMessage(throwable.getMessage());
            }
        });
    }
}

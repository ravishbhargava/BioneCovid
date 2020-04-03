package com.bione.corona.ui;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bione.corona.R;
import com.bione.corona.model.CommonResponse;
import com.bione.corona.model.onboard.SignUpDatum;
import com.bione.corona.network.ApiError;
import com.bione.corona.network.CommonParams;
import com.bione.corona.network.ResponseResolver;
import com.bione.corona.network.RestClient;
import com.bione.corona.ui.base.BaseActivity;
import com.bione.corona.utils.CommonData;
import com.bione.corona.utils.ProgressDialog;
import com.bione.corona.utils.ValidationUtil;

import java.util.List;

public class SignUpActivity extends BaseActivity {

    private AppCompatTextView tvContinue;
    private AppCompatImageView ivBack;
    private AppCompatEditText etEmail;
    private AppCompatEditText etPassword;
    private AppCompatEditText etName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etName = findViewById(R.id.etName);
        tvContinue = findViewById(R.id.tvContinue);
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
                        && ValidationUtil.checkPassword(etPassword.getText().toString())
                        && (!TextUtils.isEmpty(etName.getText().toString()))) {
                    callApi();
                } else {
                    ValidationUtil.showToast(getApplicationContext(), "Please fill the details");
                }
            }
        });
    }

    private void callApi() {
        showLoading();
        final CommonParams commonParams = new CommonParams.Builder()
                .add("name", etName.getText().toString())
                .add("email", etEmail.getText().toString())
                .add("password", etPassword.getText().toString())

                .build();

        RestClient.getApiInterface().signup(
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                etName.getText().toString())
                .enqueue(new ResponseResolver<List<SignUpDatum>>() {
                    @Override
                    public void onSuccess(List<SignUpDatum> commonResponse) {
                        hideLoading();

                        CommonData.saveUserName(etName.getText().toString());
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
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

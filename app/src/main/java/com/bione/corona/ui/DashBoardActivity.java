package com.bione.corona.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.bione.corona.R;
import com.bione.corona.model.CommonResponse;
import com.bione.corona.model.getCoronaResults.Example;
import com.bione.corona.network.ApiError;
import com.bione.corona.network.ResponseResolver;
import com.bione.corona.network.RestClient;
import com.bione.corona.ui.base.BaseActivity;
import com.bione.corona.utils.Log;

public class DashBoardActivity extends BaseActivity {

    private AppCompatTextView tvContinue;
    private AppCompatTextView tvConfirmedCases;

    private AppCompatTextView tvRecoveredCases;
    private AppCompatTextView tvDeceased;
    private AppCompatTextView tvNewCases;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        tvConfirmedCases = findViewById(R.id.tvConfirmedCases);
        tvNewCases = findViewById(R.id.tvNewCases);
        tvRecoveredCases = findViewById(R.id.tvRecoveredCases);
        tvDeceased = findViewById(R.id.tvDeceased);
        tvContinue = findViewById(R.id.tvContinue);

        tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, SurveyActivity.class);
                startActivity(intent);
            }
        });


        RestClient.getApiInterface2().getCoronaResults().enqueue(new ResponseResolver<Example>() {
            @Override
            public void onSuccess(Example commonResponse) {
                Log.d("onSuccess", "" + commonResponse.getStatewise().size());
                tvDeceased.setText("" + commonResponse.getStatewise().get(0).getDeaths());
                tvConfirmedCases.setText("" + commonResponse.getStatewise().get(0).getConfirmed());
                tvRecoveredCases.setText("" + commonResponse.getStatewise().get(0).getRecovered());
                tvNewCases.setText("" + commonResponse.getKeyValues().get(0).getConfirmeddelta());

            }

            @Override
            public void onError(ApiError error) {
//                Log.d("onError", "" + error.getMessage());
            }

            @Override
            public void onFailure(Throwable throwable) {
//                Log.d("onFailure", "" + throwable.getMessage());
            }
        });
    }


}

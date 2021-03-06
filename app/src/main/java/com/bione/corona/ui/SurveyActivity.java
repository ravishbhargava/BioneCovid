package com.bione.corona.ui;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bione.corona.R;
import com.bione.corona.ui.adapter.SurveyAdapter;
import com.bione.corona.ui.base.BaseActivity;
import com.bione.corona.utils.CustomViewPager;
import com.bione.corona.utils.Log;

public class SurveyActivity extends BaseActivity {

    private CustomViewPager viewPager;

    private ProgressBar progressBar;
    //    private AppCompatImageView ivBack;
    public static int conditionCorona = 0;
    public static int resultType = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        conditionCorona = 0;
        resultType = 0;
//        ivBack = findViewById(R.id.ivBack);
//        ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

        progressBar = findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);

        viewPager = findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPagingEnabled(false);


        // setting viewPager's pages
        final SurveyAdapter adapter = new SurveyAdapter(getSupportFragmentManager(), 12);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("onPageScrolled", "pos: " + position);
                progressBar.setProgress((position + 1));
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {  // if you want the second page, for example
                    //Your code here

                }
                Log.d("onPageSelected", "pos: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("onPageScrollStateChanged", "state: " + state);
            }
        });
    }

    public void nextStep(final int pos) {
        int currentPos = viewPager.getCurrentItem();
        viewPager.setCurrentItem(currentPos + pos);
    }
}

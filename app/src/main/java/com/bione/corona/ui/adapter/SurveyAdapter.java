package com.bione.corona.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bione.corona.ui.fragment.Question0WhoFragment;
import com.bione.corona.ui.fragment.Question2AgeFragment;
import com.bione.corona.ui.fragment.Question1GenderFragment;
import com.bione.corona.ui.fragment.Question5TempFragment;
import com.bione.corona.ui.fragment.Question3HistoryFragment;
import com.bione.corona.ui.fragment.Question4SymptomsFragment;
import com.bione.corona.ui.fragment.Question6RapidlyFragment;
import com.bione.corona.ui.fragment.Question7BreathingFragment;
import com.bione.corona.ui.fragment.Question8CoughingFragment;
import com.bione.corona.ui.fragment.Question9SymptomsFragment;
import com.bione.corona.ui.fragment.Question10TravelFragment;

public class SurveyAdapter extends FragmentPagerAdapter {

    private int numOfTabs = 0;

    public SurveyAdapter(@NonNull FragmentManager fm, final int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Question0WhoFragment();
            case 1:
                return new Question1GenderFragment();
            case 2:
                return new Question2AgeFragment();
            case 3:
                return new Question3HistoryFragment();
            case 4:
                return new Question4SymptomsFragment();
            case 5:
                return new Question5TempFragment();
            case 6:
                return new Question6RapidlyFragment();
            case 7:
                return new Question7BreathingFragment();
            case 8:
                return new Question8CoughingFragment();
            case 9:
                return new Question9SymptomsFragment();
            case 10:
                return new Question10TravelFragment();
            default:
                return null;
        }
    }

}

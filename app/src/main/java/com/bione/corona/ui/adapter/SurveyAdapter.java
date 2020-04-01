package com.bione.corona.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bione.corona.ui.fragment.Question1AgeFragment;
import com.bione.corona.ui.fragment.Question2GenderFragment;
import com.bione.corona.ui.fragment.Question3TempFragment;
import com.bione.corona.ui.fragment.Question7HistoryFragment;
import com.bione.corona.ui.fragment.Question4SymptomsFragment;
import com.bione.corona.ui.fragment.Question5SymptomsFragment;
import com.bione.corona.ui.fragment.Question6TravelFragment;
import com.bione.corona.ui.fragment.Question8ProgressFragment;

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
                return new Question1AgeFragment();
            case 1:
                return new Question2GenderFragment();
            case 2:
                return new Question3TempFragment();
            case 3:
                return new Question4SymptomsFragment();
            case 4:
                return new Question5SymptomsFragment();
            case 5:
                return new Question6TravelFragment();
            case 6:
                return new Question7HistoryFragment();
            case 7:
                return new Question8ProgressFragment();

            default:
                return null;
        }
    }

}

package com.bione.corona.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.bione.corona.R;
import com.bione.corona.ui.SurveyActivity;
import com.bione.corona.utils.KeyboardUtil;
import com.bione.corona.utils.ValidationUtil;

public class Question2AgeFragment extends Fragment {


    private View rootView;
    private Context mContext;
    private AppCompatTextView tvContinue;
    private AppCompatEditText etAge;

    public Question2AgeFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_question_one, container, false);
            tvContinue = rootView.findViewById(R.id.tvContinue);
            etAge = rootView.findViewById(R.id.etAge);

            tvContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    KeyboardUtil.hideKeyboard(getActivity());
                    if (!etAge.getText().toString().equals("")) {
                        Activity activity = getActivity();
                        if (activity instanceof SurveyActivity) {
                            SurveyActivity myActivity = (SurveyActivity) activity;
                            myActivity.nextStep(1);
                        }
                    } else {
                        ValidationUtil.showToast(mContext, "Please enter age.");
                    }
                }
            });


        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}

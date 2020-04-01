package com.bione.corona.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.bione.corona.R;
import com.bione.corona.ui.SurveyActivity;

public class QuestionOld3TempFragment extends Fragment {


    private View rootView;
    private Context mContext;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private AppCompatTextView tvContinue;

    public QuestionOld3TempFragment() {

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
            rootView = inflater.inflate(R.layout.fragment_question_three, container, false);


            tvContinue = rootView.findViewById(R.id.tvContinue);
            radioGroup = rootView.findViewById(R.id.radioGroup);

            tvContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioButton = rootView.findViewById(selectedId);
                    Toast.makeText(mContext, radioButton.getText(), Toast.LENGTH_SHORT).show();

                    Activity activity = getActivity();
                    if (activity instanceof SurveyActivity) {
                        SurveyActivity myActivity = (SurveyActivity) activity;
                        myActivity.nextStep(1);
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

package com.bione.corona.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bione.corona.R;
import com.bione.corona.model.Slots;
import com.bione.corona.ui.SurveyActivity;
import com.bione.corona.ui.fragment.adapter.CheckBoxAdapter;

import java.util.ArrayList;

public class QuestionLaterSymptomsFragment extends Fragment {


    private View rootView;
    private CheckBoxAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    private String type = "Past";
    private AppCompatTextView tvContinue;
    private AppCompatTextView tvQuestion;
    private String question = "Additionally, please specify if you are experiencing any of the symptoms below:*";

    public QuestionLaterSymptomsFragment() {

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
            rootView = inflater.inflate(R.layout.fragment_question_four, container, false);


            tvQuestion = rootView.findViewById(R.id.tvQuestion);
            tvQuestion.setText(question);
            tvContinue = rootView.findViewById(R.id.tvContinue);


            tvContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Activity activity = getActivity();
                    if (activity instanceof SurveyActivity) {
                        SurveyActivity myActivity = (SurveyActivity) activity;
                        myActivity.nextStep(1);
                    }
                }
            });

            initRecycler(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initRecycler(final View view) {
        recyclerView = view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);


        ArrayList<Slots> array = new ArrayList<>();

        Slots slots1 = new Slots("Feeling Breathless", false);
        Slots slots2 = new Slots("Difficulty in Breathing", false);
        Slots slots3 = new Slots("Pain in Chest", false);
        Slots slots4 = new Slots("Severe Weakness", false);
        Slots slots5 = new Slots("Moderate to severe cough", false);
        Slots slots6 = new Slots("Drowsiness", false);
        Slots slots7 = new Slots("None of these", false);


        array.add(slots1);
        array.add(slots2);
        array.add(slots3);
        array.add(slots4);
        array.add(slots5);
        array.add(slots6);
        array.add(slots7);


        // specify an adapter (see also next example)
        mAdapter = new CheckBoxAdapter(array);
        recyclerView.setAdapter(mAdapter);

//        // specify an adapter (see also next example)
//        mAdapter = new CheckBoxAdapter(mContext, type);
        recyclerView.setAdapter(mAdapter);
    }

}

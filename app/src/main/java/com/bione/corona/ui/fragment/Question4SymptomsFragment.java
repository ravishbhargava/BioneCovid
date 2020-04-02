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
import com.bione.corona.utils.Log;
import com.bione.corona.utils.ValidationUtil;

import java.util.ArrayList;

import static com.bione.corona.ui.SurveyActivity.resultCorona;

public class Question4SymptomsFragment extends Fragment {


    private View rootView;
    private CheckBoxAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    private String type = "Past";
    private AppCompatTextView tvContinue;
    private AppCompatTextView tvQuestion;
    private String question = "Do you have any of the following symptoms? Please select symptoms that are not related to any chronic disease you may be subject to";

    public Question4SymptomsFragment() {

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

                    ArrayList<Slots> selectedArray = new ArrayList<>();
                    for (int s = 0; s < mAdapter.getSlots().size(); s++) {
                        if (mAdapter.getSlots().get(s).isSelected()) {
                            selectedArray.add(mAdapter.getSlots().get(s));
                        }
                    }
                    if (selectedArray.size() > 0) {
                        Activity activity = getActivity();
                        if (activity instanceof SurveyActivity) {
                            SurveyActivity myActivity = (SurveyActivity) activity;
                            ArrayList<Slots> arrayList = mAdapter.getSlots();

                            if (arrayList.get(0).isSelected() && arrayList.get(1).isSelected()) {
                                myActivity.nextStep(1);
                            } else if (arrayList.get(0).isSelected() && arrayList.get(2).isSelected()) {
                                myActivity.nextStep(1);
                            } else if (arrayList.get(1).isSelected() && arrayList.get(2).isSelected()) {
                                myActivity.nextStep(2);
                            } else if (arrayList.get(0).isSelected()) {
                                myActivity.nextStep(1);
                            } else if (arrayList.get(1).isSelected()) {
                                myActivity.nextStep(2);
                            } else if (arrayList.get(2).isSelected()) {
                                myActivity.nextStep(2);
                            } else {
                                myActivity.nextStep(5);
                            }
                        }
                    } else {
                        ValidationUtil.showToast(mContext);
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

        Slots slots1 = new Slots("Fever", false);
        Slots slots2 = new Slots("Cough", false);
        Slots slots3 = new Slots("Shortness of Breath", false);
        Slots slots4 = new Slots("None of these", false);


        array.add(slots1);
        array.add(slots2);
        array.add(slots3);
        array.add(slots4);


        // specify an adapter (see also next example)
        mAdapter = new CheckBoxAdapter(array);
        recyclerView.setAdapter(mAdapter);

//        // specify an adapter (see also next example)
//        mAdapter = new RadioAdapter(mContext, type);
        recyclerView.setAdapter(mAdapter);
    }

}

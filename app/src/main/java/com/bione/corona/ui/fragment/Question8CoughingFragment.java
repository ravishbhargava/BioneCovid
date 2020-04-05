package com.bione.corona.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.bione.corona.ui.ResultActivity;
import com.bione.corona.ui.SurveyActivity;
import com.bione.corona.ui.fragment.adapter.RadioAdapter;
import com.bione.corona.utils.Log;
import com.bione.corona.utils.ValidationUtil;

import java.util.ArrayList;

import static com.bione.corona.ui.SurveyActivity.conditionCorona;

public class Question8CoughingFragment extends Fragment {


    private View rootView;
    private RadioAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    private String type = "Past";
    private AppCompatTextView tvContinue;
    private AppCompatTextView tvQuestion;
    private String question = "Are you coughing up blood?";

    public Question8CoughingFragment() {

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

                    if (mAdapter.getCheckedPosition() >= 0) {
                        if (mAdapter.getCheckedPosition() == 0) {
                            conditionCorona = conditionCorona + 1;
                        }
                        Activity activity = getActivity();
                        if (activity instanceof SurveyActivity) {
                            SurveyActivity myActivity = (SurveyActivity) activity;
                            Log.d("conditionCorona", ": " + conditionCorona);
                            if (conditionCorona > 0) {
                                Intent intent = new Intent(myActivity, ResultActivity.class);
                                intent.putExtra("type", "3");
                                startActivity(intent);
                                myActivity.finish();
                            } else {
                                myActivity.nextStep(1);
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

        Slots slots1 = new Slots("Yes", false);
        Slots slots2 = new Slots("No", false);
//        Slots slots3 = new Slots("High Fever > 102 F", false);
//        Slots slots4 = new Slots("Close contact with confirmed COVID-19 case in last 14 days", false);


        array.add(slots1);
        array.add(slots2);
//        array.add(slots3);
//        array.add(slots4);


        // specify an adapter (see also next example)
        mAdapter = new RadioAdapter(array);
        recyclerView.setAdapter(mAdapter);

//        // specify an adapter (see also next example)
//        mAdapter = new RadioAdapter(mContext, type);
        recyclerView.setAdapter(mAdapter);
    }

}

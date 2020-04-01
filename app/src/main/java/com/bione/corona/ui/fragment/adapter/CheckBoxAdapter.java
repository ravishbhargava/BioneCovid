package com.bione.corona.ui.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bione.corona.R;
import com.bione.corona.model.Slots;

import java.util.ArrayList;


public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.MyViewHolder> {

    private String type = "";
    private Context mContext;
    private ArrayList<Slots> slots;

    public CheckBoxAdapter(final ArrayList<Slots> slots) {
        this.slots = slots;
    }

    public CheckBoxAdapter(final Context mContext, final String type) {
        this.type = type;
        this.mContext = mContext;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        private TextView tvAnswer;
        private ImageView ivSelected;

        private LinearLayoutCompat llSlots;

        public MyViewHolder(View v) {
            super(v);
            view = v;
            ivSelected = v.findViewById(R.id.ivSelected);
            tvAnswer = v.findViewById(R.id.tvAnswer);
            llSlots = v.findViewById(R.id.llSlots);
        }
    }

    @NonNull
    @Override
    public CheckBoxAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_slots_check, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tvAnswer.setText(slots.get(position).name);

        if (slots.get(position).isSelected()) {
            holder.ivSelected.setVisibility(View.VISIBLE);
            holder.llSlots.setBackgroundResource(R.drawable.drawable_selected_border);
        } else {
            holder.ivSelected.setVisibility(View.INVISIBLE);
            holder.llSlots.setBackgroundResource(R.drawable.drawable_unselected_border);
        }


        holder.llSlots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItem(position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return slots.size();
    }

    public void selectedItem(int position) {
        if (!slots.get(position).isSelected()) {
            slots.get(position).setSelected(true);
        } else {
            slots.get(position).setSelected(false);
        }
        notifyDataSetChanged();
    }
}

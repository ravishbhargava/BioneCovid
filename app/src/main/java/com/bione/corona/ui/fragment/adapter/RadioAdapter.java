package com.bione.corona.ui.fragment.adapter;

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


public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.MyViewHolder> {

    private ArrayList<Slots> slots;
    private int checkedPosition = -1;

    public RadioAdapter(final ArrayList<Slots> slots) {

        this.slots = slots;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_slots_radio, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvAnswer.setText(slots.get(position).name);
//
//        if (slots.get(position).getName().equals("NO")) {
////            holder.tvNotAvailable.setVisibility(View.VISIBLE);
//            holder.ivSelected.setVisibility(View.GONE);
//        } else {
//            holder.tvNotAvailable.setVisibility(View.GONE);
        holder.ivSelected.setVisibility(View.GONE);

        if (checkedPosition == -1) {
            holder.ivSelected.setVisibility(View.GONE);
        } else {
            if (checkedPosition == position) {
                holder.ivSelected.setVisibility(View.VISIBLE);
            } else {
                holder.ivSelected.setVisibility(View.GONE);
            }
        }
        holder.llSlots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!slots.get(position).getName().equals("NO")) {
                    holder.ivSelected.setVisibility(View.VISIBLE);

                    if (checkedPosition != position) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = position;
                    }
                }
            }
        });
//        }
    }

    @Override
    public int getItemCount() {
        return this.slots.size();
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

}


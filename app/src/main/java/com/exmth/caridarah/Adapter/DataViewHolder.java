package com.exmth.caridarah.Adapter;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.exmth.caridarah.R;

public class DataViewHolder extends RecyclerView.ViewHolder {

    public TextView mBloodTypeTextView, mFullNameTextView, mPhoneNumberTextView, mDateTextView, mTimeTextView;
    public GradientDrawable mBloodTypeCircleColorDrawable;

    public DataViewHolder(View itemView) {
        super(itemView);
        mBloodTypeTextView = itemView.findViewById(R.id.blood_type);
        mFullNameTextView = itemView.findViewById(R.id.full_name);
        mPhoneNumberTextView = itemView.findViewById(R.id.phone_number);
        mDateTextView = itemView.findViewById(R.id.date);
        mTimeTextView = itemView.findViewById(R.id.time);

        mBloodTypeCircleColorDrawable = (GradientDrawable) mBloodTypeTextView.getBackground();
    }
}

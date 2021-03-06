package com.exmth.caridarah.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exmth.caridarah.Model.Donor;
import com.exmth.caridarah.R;
import com.exmth.caridarah.Utils.UIHelpers;

import java.util.List;

import static com.exmth.caridarah.Utils.Constant.DONOR_ADAPTER;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = DataAdapter.class.getSimpleName();

    private Context mContext;
    private List<Donor> mDonorList;
    private int mAdapterType;

    public DataAdapter(Context context, List<Donor> donorList, int adapterType) {
        mContext = context;
        mDonorList = donorList;
        mAdapterType = adapterType;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataViewHolder viewHolder = (DataViewHolder) holder;

        if (mAdapterType == DONOR_ADAPTER) {
            Donor donor = mDonorList.get(position);

            int circleColor = UIHelpers.getBloodTypeCircleColor(mContext, donor.getBloodType());
            viewHolder.mBloodTypeCircleColorDrawable.setColor(circleColor);

            viewHolder.mBloodTypeTextView.setText(donor.getBloodType());
            viewHolder.mFullNameTextView.setText(donor.getFullName());
            viewHolder.mPhoneNumberTextView.setText(donor.getPhoneNumber());
            viewHolder.mDateTextView.setText(donor.getTimestamp());
            viewHolder.mTimeTextView.setText(donor.getTimestamp());
        } else {

        }
    }

    @Override
    public int getItemCount() {
        if (mAdapterType == DONOR_ADAPTER) {
            return mDonorList.size();
        }
        return 0;
    }
}

package com.exmth.caridarah.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.format.DateUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.exmth.caridarah.R;

import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_A;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_AB;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_AB_MINUS;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_AB_PLUS;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_A_MINUS;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_A_PLUS;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_B;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_B_MINUS;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_B_PLUS;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_O_MINUS;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_O_PLUS;

public class UIHelpers {
    public static ArrayAdapter<String> staticListSpinnerAdapter(final Context context, String[] list) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, list) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView tv = (TextView) v;

//                Typeface externalFont=Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
//                tv.setTypeface(externalFont);
//                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                v.setPadding(0,0,0,0);

                if (position == 0) {
                    tv.setTextColor(context.getResources().getColor(R.color.colorTextInputHint));
                } else {
                    tv.setTextColor(context.getResources().getColor(R.color.colorTextInput));
                }

//                tv.setTextColor(context.getResources().getColor(R.color.colorTextInput));

                return v;
            }

            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) v;

//                Typeface externalFont=Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
//                tv.setTypeface(externalFont);
//                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

                if (position == 0) {
                    tv.setTextColor(context.getResources().getColor(R.color.colorTextInputHint));
                } else {
                    tv.setTextColor(context.getResources().getColor(R.color.colorTextInput));
                }
//                tv.setTextColor(context.getResources().getColor(R.color.colorTextInput));

                return v;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public static int getBloodTypeCircleColor(Context context, String bloodType) {
        int circleColorId;
        switch (bloodType) {
            case BLOOD_TYPE_A:
                circleColorId = R.color.colorBloodA;
                break;
            case BLOOD_TYPE_A_PLUS:
                circleColorId = R.color.colorBloodAPlus;
                break;
            case BLOOD_TYPE_A_MINUS:
                circleColorId = R.color.colorBloodAMinus;
                break;
            case BLOOD_TYPE_B:
                circleColorId = R.color.colorBloodB;
                break;
            case BLOOD_TYPE_B_PLUS:
                circleColorId = R.color.colorBloodBPlus;
                break;
            case BLOOD_TYPE_B_MINUS:
                circleColorId = R.color.colorBloodBMinus;
                break;
            case BLOOD_TYPE_AB:
                circleColorId = R.color.colorBloodAB;
                break;
            case BLOOD_TYPE_AB_PLUS:
                circleColorId = R.color.colorBloodABPlus;
                break;
            case BLOOD_TYPE_AB_MINUS:
                circleColorId = R.color.colorBloodABMinus;
                break;
            case BLOOD_TYPE_O_PLUS:
                circleColorId = R.color.colorBloodOPlus;
                break;
            case BLOOD_TYPE_O_MINUS:
                circleColorId = R.color.colorBloodOMinus;
                break;
            default:
                circleColorId = R.color.colorBloodO;
                break;
        }
        return ContextCompat.getColor(context, circleColorId);
    }

    public static CharSequence convertTimestamp(String time){
        return DateUtils.getRelativeTimeSpanString(Long.parseLong(time),System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
    }
}

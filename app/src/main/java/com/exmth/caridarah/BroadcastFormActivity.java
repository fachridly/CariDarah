package com.exmth.caridarah;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.exmth.caridarah.Utils.UIHelpers;

import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_A;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_AB;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_B;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_O;

public class BroadcastFormActivity extends AppCompatActivity {

    private Spinner mBloodTypeSpinner;
    private TextInputEditText mQuantityEditText, mMessageEditText;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_form);

        initilizeView();

        mSendButton.setOnClickListener(broadcastClickListener());

        String[] list = new String[]{
                getString(R.string.hint_blood_type_spinner),
                BLOOD_TYPE_A,
                BLOOD_TYPE_B,
                BLOOD_TYPE_AB,
                BLOOD_TYPE_O
        };

        ArrayAdapter<String> adapter = UIHelpers.staticListSpinnerAdapter(this, list);
        mBloodTypeSpinner.setAdapter(adapter);
    }

    private void initilizeView() {
        mBloodTypeSpinner = findViewById(R.id.blood_type_spinner);
        mQuantityEditText = findViewById(R.id.quantity_edit_text);
        mMessageEditText = findViewById(R.id.message_edit_text);
        mSendButton = findViewById(R.id.broadcast_button);
    }

    private View.OnClickListener broadcastClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }
}

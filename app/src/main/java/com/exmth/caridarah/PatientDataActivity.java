package com.exmth.caridarah;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PatientDataActivity extends AppCompatActivity {
    private FloatingActionButton mAddNewPatientDataFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);

        initializeView();
        mAddNewPatientDataFAB.setOnClickListener(newPatientDataClickListener());
    }

    private void initializeView() {
        mAddNewPatientDataFAB = findViewById(R.id.add_new_patient_data_fab);
    }

    private View.OnClickListener newPatientDataClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientDataActivity.this, PatientDataFormActivity.class));
            }
        };
    }
}

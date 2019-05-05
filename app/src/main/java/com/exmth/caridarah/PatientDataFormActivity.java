package com.exmth.caridarah;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.exmth.caridarah.Model.Donor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientDataFormActivity extends BaseActivity {
    private final static String TAG = RegisterActivity.class.getSimpleName();

    private Spinner bloodTypeSpinner;
    private TextInputEditText mNameEditText, mPhoneEditText;
    private Button mSaveButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data_form);

        initializeView();

        mSaveButton.setOnClickListener(saveClickListener());

        mAuth = FirebaseAuth.getInstance();
    }

    private void initializeView() {
        bloodTypeSpinner = findViewById(R.id.blood_type_spinner);

        mNameEditText = findViewById(R.id.name_edit_text);
        mPhoneEditText = findViewById(R.id.phone_edit_text);

        mSaveButton = findViewById(R.id.save_button);
    }

    private View.OnClickListener saveClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bloodType = bloodTypeSpinner.getSelectedItem().toString();
                String fullName = mNameEditText.getText().toString();
                String phoneNumber = mPhoneEditText.getText().toString();

                Calendar calFordDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                String date = currentDate.format(calFordDate.getTime());

                Calendar calFordTime = Calendar.getInstance();
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
                String time = currentTime.format(calFordDate.getTime());

                final Donor newDonor = new Donor(bloodType, fullName, phoneNumber, date, time);

                final FirebaseUser patient = mAuth.getCurrentUser();

                DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("patient");
                database.child(patient.getUid()).setValue(newDonor).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(PatientDataFormActivity.this, PatientDataActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        };
    }
}

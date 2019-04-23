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

public class DonorDataFormActivity extends BaseActivity {
    private final static String TAG = RegisterActivity.class.getSimpleName();

    private Spinner spinnerBloodType;
    private TextInputEditText nameEditText, phoneEditText;
    private Button buttonSave;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_data_form);

        initializeView();

        buttonSave.setOnClickListener(saveClickListener());

        mAuth = FirebaseAuth.getInstance();
    }

    private void initializeView() {
        spinnerBloodType = findViewById(R.id.spinner_blood_type);

        nameEditText = findViewById(R.id.name_edit);
        phoneEditText = findViewById(R.id.phone_edit);

        buttonSave = findViewById(R.id.button_save);
    }

    private View.OnClickListener saveClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bloodType = spinnerBloodType.getSelectedItem().toString();
                String fullName = nameEditText.getText().toString();
                String phoneNumber = phoneEditText.getText().toString();

                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm a");
                String timestamp = sdf.format(date);

                final Donor newDonor = new Donor(bloodType, fullName, phoneNumber, timestamp);

                final FirebaseUser pendonor = mAuth.getCurrentUser();

                DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("pendonor");
                database.child(pendonor.getUid()).setValue(newDonor).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(DonorDataFormActivity.this, DonorDataActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        };
    }
}
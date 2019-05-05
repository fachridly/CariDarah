package com.exmth.caridarah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class AdminMainActivity extends AppCompatActivity {
    ImageButton mBroadcastButton, mDonorButton, mPatientButton, mLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        initializeView();
        mLogoutButton.setOnClickListener(logoutClickListener());
        mBroadcastButton.setOnClickListener(broadcastClickListener());
        mDonorButton.setOnClickListener(donorDataClickListener());
        mPatientButton.setOnClickListener(patientDataClickListener());
    }

    private void initializeView() {
        mBroadcastButton = findViewById(R.id.broadcast_button);
        mDonorButton = findViewById(R.id.donor_button);
        mPatientButton = findViewById(R.id.patient_button);
        mLogoutButton = findViewById(R.id.logout_button);
    }

    private View.OnClickListener broadcastClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, BroadcastFormActivity.class));
            }
        };
    }

    private View.OnClickListener donorDataClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, DonorDataActivity.class));
            }
        };
    }

    private View.OnClickListener patientDataClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, PatientDataActivity.class));
            }
        };
    }

    private View.OnClickListener logoutClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, LoginActivity.class));
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        };
    }
}

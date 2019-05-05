package com.exmth.caridarah;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.exmth.caridarah.Adapter.DataAdapter;
import com.exmth.caridarah.Model.Donor;

public class PatientDataActivity extends AppCompatActivity {
    private FloatingActionButton mAddNewPatientDataFAB;
    private RecyclerView mPatientRecyclerView;
    private RecyclerView.Adapter mPatientAdapter;

    private FirebaseAuth mAuth;
    private DatabaseReference mPatientDatabase;

    private ArrayList<Donor> mDonorList;

    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);

        initializeView();
        mAddNewPatientDataFAB.setOnClickListener(newPatientDataClickListener());

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        mPatientDatabase = FirebaseDatabase.getInstance().getReference().child("patient");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        mPatientRecyclerView.setLayoutManager(linearLayoutManager);
        mPatientRecyclerView.setAdapter(mPatientAdapter);
        mPatientRecyclerView.setHasFixedSize(true);

        DisplayPatientRecyclerView();
    }

    private void DisplayPatientRecyclerView() {
        mPatientDatabase.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Inisialisasi ArrayList
                mDonorList = new ArrayList<>();

                //Inisialisasi Adapter dan data Mahasiswa dalam bentuk Array
                mPatientAdapter = new DataAdapter(mDonorList, PatientDataActivity.this);

                //Memasang Adapter pada RecyclerView
                mPatientRecyclerView.setAdapter(mPatientAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initializeView() {
        mAddNewPatientDataFAB = findViewById(R.id.add_new_patient_data_fab);
        mPatientRecyclerView = findViewById(R.id.patient_recycler_view);
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

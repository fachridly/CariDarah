package com.exmth.caridarah;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.exmth.caridarah.Adapter.DataAdapter;
import com.exmth.caridarah.Model.Donor;
import com.exmth.caridarah.Utils.Constant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonorDataActivity extends AppCompatActivity {
    private static final String TAG = DonorDataActivity.class.getSimpleName();

    private RecyclerView mDonorRecyclerView;
    private RecyclerView.Adapter mDonorAdapter;
    private FloatingActionButton mAddNewDonorDataFAB;

    private FirebaseAuth mAuth;
    private DatabaseReference mDonorDatabase;

    private ArrayList<Donor> mDonorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_data);

        initializeView();
        mAddNewDonorDataFAB.setOnClickListener(newDonorDataClickListener());

        mAuth = FirebaseAuth.getInstance();

        MyRecyclerView();
        DisplayDonorRecyclerView();
    }

    private void initializeView() {
        mDonorRecyclerView = findViewById(R.id.donor_recycler_view);
        mAddNewDonorDataFAB = findViewById(R.id.add_new_donor_data_fab);
    }

    private View.OnClickListener newDonorDataClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonorDataActivity.this, DonorDataFormActivity.class));
            }
        };
    }

    private void DisplayDonorRecyclerView() {

        mDonorDatabase = FirebaseDatabase.getInstance().getReference().child("donor");
        mDonorDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Inisialisasi ArrayList
                mDonorList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //Mapping data pada DataSnapshot ke dalam objek donor
                    Donor donor = snapshot.getValue(Donor.class);
                    mDonorList.add(donor);
                }

                //Inisialisasi Adapter dan data Donor dalam bentuk Array
                mDonorAdapter = new DataAdapter(getApplicationContext(), mDonorList, Constant.DONOR_ADAPTER);

                //Memasang Adapter pada RecyclerView
                mDonorRecyclerView.setAdapter(mDonorAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Metode yang berisi kumpulan baris kode untuk mengatur RecyclerView
    private void MyRecyclerView(){
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mDonorRecyclerView.setLayoutManager(linearLayoutManager);
        mDonorRecyclerView.setHasFixedSize(true);

        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        mDonorRecyclerView.addItemDecoration(itemDecoration);
    }
}

package com.exmth.caridarah;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.exmth.caridarah.Adapter.DataAdapter;
import com.exmth.caridarah.Model.Donor;

import java.util.ArrayList;
import java.util.List;

import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_A;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_A_PLUS;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_O;
import static com.exmth.caridarah.Utils.Constant.BLOOD_TYPE_O_MINUS;
import static com.exmth.caridarah.Utils.Constant.DONOR_ADAPTER;

public class DonorDataActivity extends AppCompatActivity {
    private RecyclerView mDonorRecyclerView;
    private FloatingActionButton mAddNewDonorDataFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_data);

        initializeView();
        mAddNewDonorDataFAB.setOnClickListener(newDonorDataClickListener());

        List<Donor> donorList = new ArrayList<>();
        donorList.add(new Donor(BLOOD_TYPE_A_PLUS, "Ayya", "08577xxxx", "12345"));
        donorList.add(new Donor(BLOOD_TYPE_O_MINUS, "Fachri", "08577xxxx", "12345"));

        DataAdapter adapter = new DataAdapter(this, donorList, DONOR_ADAPTER);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mDonorRecyclerView.setLayoutManager(linearLayoutManager);
        mDonorRecyclerView.setAdapter(adapter);
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
}

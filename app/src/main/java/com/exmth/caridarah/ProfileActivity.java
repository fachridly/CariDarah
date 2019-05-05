package com.exmth.caridarah;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private final static String TAG = ProfileActivity.class.getSimpleName();

    private TextInputEditText userEmail, userName, userPhone;

    private FirebaseAuth mAuth;

    private DatabaseReference database;

    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeView();

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance().getReference().child("user").child(currentUserId);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String myEmail = dataSnapshot.child("email").getValue().toString();
                    String myName = dataSnapshot.child("name").getValue().toString();
                    String myPhone = dataSnapshot.child("phone").getValue().toString();

                    userEmail.setText(myEmail);
                    userName.setText(myName);
                    userPhone.setText(myPhone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initializeView() {
        userEmail = findViewById(R.id.email_view);
        userName = findViewById(R.id.name_view);
        userPhone = findViewById(R.id.phone_view);
    }
}

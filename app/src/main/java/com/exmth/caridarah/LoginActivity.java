package com.exmth.caridarah;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends BaseActivity {
    private final static String TAG = LoginActivity.class.getSimpleName();

    private TextInputEditText mEmailEditText, mPasswordEditText;
    private Button mRegisterButton, mLoginButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeView();

        mRegisterButton.setOnClickListener(registerClickListener());
        mLoginButton.setOnClickListener(loginClickListener());

        mAuth = FirebaseAuth.getInstance();
    }

    private void initializeView() {
        mEmailEditText = findViewById(R.id.email_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);

        mRegisterButton = findViewById(R.id.register_button);
        mLoginButton = findViewById(R.id.login_button);
    }

    private View.OnClickListener registerClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    private View.OnClickListener loginClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                if (isValidate(email, password)) {
                    showProgressDialog();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "sign in success");
                                        final FirebaseUser user = mAuth.getCurrentUser();

                                        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("admin");
                                        database.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                Intent intent = null;
                                                for (DataSnapshot list : dataSnapshot.getChildren()) {
                                                    if (list.getKey().equals(user.getUid())) {
                                                        intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                                                        break;
                                                    } else {
                                                        intent = new Intent(LoginActivity.this, MainActivity.class);
                                                    }
                                                }
                                                hideProgressDialog();
                                                if (intent != null) startActivity(intent);
                                                finish();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                Log.d(TAG, "onCancelled: database admin");
                                                hideProgressDialog();
                                            }
                                        });
                                    } else {
                                        Log.d(TAG, "sign in failure");
                                        hideProgressDialog();
                                        Toast.makeText(LoginActivity.this, "Terdapat kesalahan pada koneksi, email, atau password anda.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        };
    }

    private boolean isValidate(String email, String password){
        if (email.isEmpty()) {
            Toast.makeText(this, "Isi email terlebih dahulu", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Isi password terlebih dahulu", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

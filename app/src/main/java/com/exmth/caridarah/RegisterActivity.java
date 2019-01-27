package com.exmth.caridarah;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.exmth.caridarah.Model.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends BaseActivity {
    private final static String TAG = RegisterActivity.class.getSimpleName();

    private TextInputEditText mEmailEditText, mPasswordEditText, mConfirmEditText, mNameEditText, mPhoneEditText;
    private Button mRegisterButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeView();

        mRegisterButton.setOnClickListener(registerClickListener());

        mAuth = FirebaseAuth.getInstance();
    }

    private void initializeView() {
        mEmailEditText = findViewById(R.id.email_register_edit_text);
        mPasswordEditText = findViewById(R.id.password_register_edit_text);
        mConfirmEditText = findViewById(R.id.confirm_password_register_edit_text);
        mNameEditText = findViewById(R.id.name_register_edit_text);
        mPhoneEditText = findViewById(R.id.phone_register_edit_text);

        mRegisterButton = findViewById(R.id.register_button);
    }

    private View.OnClickListener registerClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String confirmPass = mConfirmEditText.getText().toString();
                String name = mNameEditText.getText().toString();
                String phone = mPhoneEditText.getText().toString();

                final Client newClient = new Client(email, password, name, phone);

                if (isValidate(newClient, confirmPass)) {
                    showProgressDialog();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "sign up success");
                                        final FirebaseUser user = mAuth.getCurrentUser();

                                        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("user");
                                        database.child(user.getUid()).setValue(newClient).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        });
                                    } else {
                                        Log.d(TAG, "sign in failure");
                                        hideProgressDialog();
                                        Toast.makeText(RegisterActivity.this, "Terdapat kesalahan pada koneksi, email, atau password anda.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        };
    }

    private boolean isValidate(Client client, String confirmPass){
        if (client.getEmail().isEmpty()) {
            Toast.makeText(this, "Isi email terlebih dahulu", Toast.LENGTH_SHORT).show();
            return false;
        } else if (client.getPassword().isEmpty()) {
            Toast.makeText(this, "Isi password terlebih dahulu", Toast.LENGTH_SHORT).show();
            return false;
        } else if(confirmPass.isEmpty()){
            Toast.makeText(this, "Isi konfirmasi password terlebih dahulu", Toast.LENGTH_SHORT).show();
            return false;
        } else if (client.getName().isEmpty()) {
            Toast.makeText(this, "Isi nama terlebih dahulu", Toast.LENGTH_SHORT).show();
            return false;
        } else if (client.getPhone().isEmpty()) {
            Toast.makeText(this, "Isi nomor telepon/ handphone terlebih dahulu", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!client.getPassword().equals(confirmPass)) {
            Toast.makeText(this, "Password dan konfirmasi password tidak sesuai", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}

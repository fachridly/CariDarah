package com.exmth.caridarah;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.exmth.caridarah.Model.Client;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText mEmailEditText, mPasswordEditText, mNameEditText, mPhoneEditText;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeView();

        mRegisterButton.setOnClickListener(registerClickListener());
    }

    private void initializeView() {
        mEmailEditText = findViewById(R.id.email_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);
        mNameEditText = findViewById(R.id.name_register_edit_text);
        mPhoneEditText = findViewById(R.id.phone_register_edit_text);

        mRegisterButton = findViewById(R.id.register_button);
    }

    private View.OnClickListener registerClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String name = mNameEditText.getText().toString();
                String phone = mPhoneEditText.getText().toString();

                Client newClient = new Client(email, password, name, phone);*/

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}

package com.exmth.caridarah;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText mEmailEditText, mPasswordEditText;
    private Button mRegisterButton, mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeView();

        mRegisterButton.setOnClickListener(registerClickListener());
        mLoginButton.setOnClickListener(loginClickListener());
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

                Intent intent;
                if (email.equalsIgnoreCase("admin")) {
                    intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                } else {
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        };
    }
}

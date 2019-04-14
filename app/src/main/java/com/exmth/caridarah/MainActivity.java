package com.exmth.caridarah;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageButton mProfileButton, mInformationButton, mNotificationButton, mLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
        mLogoutButton.setOnClickListener(logoutClickListener());
        mProfileButton.setOnClickListener(profileClickListener());
        mInformationButton.setOnClickListener(informationClickListener());
        mNotificationButton.setOnClickListener(notificationClickListener());

        // CARA TIMESTAMP
        /*long timestamp = new Date().getTime();
        Date date = new Date(timestamp);

        String patternDate = "MMM dd, yyyy hh:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDate);

        String dateFormated = simpleDateFormat.format(date);
        Toast.makeText(this, dateFormated, Toast.LENGTH_LONG).show();*/

    }

    private void initializeView() {
        mProfileButton = findViewById(R.id.profile_button);
        mInformationButton = findViewById(R.id.information_button);
        mNotificationButton = findViewById(R.id.notification_button);
        mLogoutButton = findViewById(R.id.logout_button);
    }

    private View.OnClickListener logoutClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        };
    }

    private View.OnClickListener profileClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        };
    }

    private View.OnClickListener informationClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InformationActivity.class));
            }
        };
    }

    private View.OnClickListener notificationClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        };
    }
}

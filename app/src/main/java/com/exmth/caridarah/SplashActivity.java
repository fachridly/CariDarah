package com.exmth.caridarah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

//        Ketika splash dibuat, akan langsung dilemparkan ke activity login
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));

//        di-finish agar ketika kelempar ke menu login tidak dapat melakukan back ke activity login
        finish(); 
    }
}

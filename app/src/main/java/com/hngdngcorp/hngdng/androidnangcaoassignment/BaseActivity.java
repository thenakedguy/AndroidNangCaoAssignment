package com.hngdngcorp.hngdng.androidnangcaoassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public void navigate(Class aClass){
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }
}

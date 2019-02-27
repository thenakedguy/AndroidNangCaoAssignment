package com.hngdngcorp.hngdng.androidnangcaoassignment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends BaseActivity {
    private EditText edtKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// keyHash ok75B0+NIbIyCKiQQY7gsdVmKlU=
    }

    public void openCourse(View view) {

    }

    public void openMaps(View view) {
        navigate(MapsActivity.class);
    }

    public void openNews(View view) {
        navigate(RSSFeedAcitvity.class);
    }

    public void openSocial(View view) {
        navigate(ShareActivity.class);
    }
}

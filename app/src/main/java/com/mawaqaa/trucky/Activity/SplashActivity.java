package com.mawaqaa.trucky.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mawaqaa.trucky.OtherClasses.SharedPrefsUtils;
import com.mawaqaa.trucky.OtherClasses.appConstants;
import com.mawaqaa.trucky.R;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    TextView english, arabic;
    SharedPrefsUtils mypref;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        defineView();

        try {

            if (ActivityCompat.checkSelfPermission(SplashActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SplashActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(SplashActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

            }
        }
        catch (Exception xx){}

    }

    private void defineView() {
        english = findViewById(R.id.english);
        arabic = findViewById(R.id.arabic);

        english.setOnClickListener(this);
        arabic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.english:
                mypref.setIntegerPreference(this, appConstants.languageKey, 1);
                i = new Intent(this, MainMenuActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.arabic:
                mypref.setIntegerPreference(this, appConstants.languageKey, 2);
                i = new Intent(this, MainMenuActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}

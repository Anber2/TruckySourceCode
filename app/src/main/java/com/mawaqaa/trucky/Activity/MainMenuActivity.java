package com.mawaqaa.trucky.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.mawaqaa.trucky.OtherClasses.appConstants;
import com.mawaqaa.trucky.R;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout find_truck, live_station, resturent;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);

        definView();
    }

    private void definView() {
        find_truck = findViewById(R.id.find_truck);
        live_station = findViewById(R.id.live_station);
        resturent = findViewById(R.id.resturent);
        find_truck.setOnClickListener(this);
        live_station.setOnClickListener(this);
        resturent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_truck:
                appConstants.type_services = 1 ;

                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.live_station:
                appConstants.type_services = 2 ;
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.resturent:
                appConstants.type_services = 3 ;

                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
        }
    }
}

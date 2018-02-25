package com.mawaqaa.trucky.Fragement;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mawaqaa.trucky.Activity.MainActivity;
import com.mawaqaa.trucky.Adapter.MenuItemAdapter;
import com.mawaqaa.trucky.Models.MenuItemModel;
import com.mawaqaa.trucky.OtherClasses.appConstants;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.interfaces.BottomBarButtonClickState;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.mawaqaa.trucky.Activity.MainActivity.search_btn;

public class LiveStationFragment extends MainBaseFragment {

    View v ;
    RecyclerView station_recy ;
    private LinearLayoutManager linearLayoutManager;
    List<MenuItemModel> stationtList = new ArrayList<MenuItemModel>();
    MenuItemAdapter stationItemAdapter ;
    Spinner location , cuisine_type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.live_station_fragment, container, false);
        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.live_station));

        BottomBarButtonClickState buttonClickState = (BottomBarButtonClickState) Activity;
        buttonClickState.LiveStation();
        search_btn.setVisibility(View.VISIBLE);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Holder holder;
                holder = new ViewHolder(R.layout.station_top_dialog);
                final DialogPlus dialog = DialogPlus.newDialog(getContext())
                        .setContentHolder(holder)
                        .setCancelable(true)
                        .setGravity(Gravity.TOP)
                        .setBackgroundColorResId(Color.TRANSPARENT)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(DialogPlus dialog, View view) {
                                switch (view.getId()) {
                                    case R.id.find :
                                        dialog.dismiss();
                                        Activity.pushFragments(new FoodTrucksFragment(), true, true);
                                        break;

                                    case R.id.view_dialog:
                                        dialog.dismiss();
                                        break;
                                    case R.id.loc_btn:
                                        location.performClick();
                                        break;

                                    case R.id.cuisine_type_btn:
                                        cuisine_type.performClick();
                                        break;
                                }
                            }
                        })
                        .create();

                View view = dialog.getHolderView();
                location = view.findViewById(R.id.location);
                cuisine_type = view.findViewById(R.id.cuisine_type);

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Activity, android.R.layout.simple_spinner_item, MainActivity.areaArray);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                location.setAdapter(spinnerArrayAdapter);

                ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(Activity, android.R.layout.simple_spinner_item, MainActivity.cuisineArray);
                spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cuisine_type.setAdapter(spinnerArrayAdapter2);

                dialog.show();
            }
        });

        defineView();

        return v ;
    }

    private void defineView() {
        station_recy = v.findViewById(R.id.station_recy);
        linearLayoutManager = new LinearLayoutManager(Activity);
        station_recy.setLayoutManager(linearLayoutManager);

        fillStationData();
    }

    private void fillStationData() {
        stationtList.clear();
        for (int i = 0 ; i<3 ; i++){
            stationtList.add(new MenuItemModel(
                    0 ,
                    "Resturent",
                    "http://al-feel.com/logo.jpg",
                    "Italian",
                    "1",
                    false
            ));
        }

        stationItemAdapter = new MenuItemAdapter(Activity,stationtList);
        station_recy.setAdapter(stationItemAdapter);

        stationItemAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                appConstants.rest_image = stationtList.get(position).getImage();
                appConstants.rest_id = stationtList.get(position).getId();
                appConstants.rest_name=stationtList.get(position).getName();
                appConstants.rest_cusione = stationtList.get(position).getCusine();
                appConstants.rest_rate = stationtList.get(position).getRate();
                appConstants.rest_isFavorite = stationtList.get(position).isFavorite();

                Activity.pushFragments(new StationMenuFragment(), true, true);
            }
        });
    }
}
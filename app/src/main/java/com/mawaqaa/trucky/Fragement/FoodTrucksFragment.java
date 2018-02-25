package com.mawaqaa.trucky.Fragement;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
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

public class FoodTrucksFragment extends MainBaseFragment implements OnMapReadyCallback {

    RecyclerView food_truck_recy ;
    private LinearLayoutManager linearLayoutManager;
    List<MenuItemModel> stationtList = new ArrayList<MenuItemModel>();
    MenuItemAdapter stationItemAdapter ;
    View v;
    private GoogleMap mGoogleMap;
    static double latitude = 29.375442;
    static double longitude = 47.991212;

    SupportMapFragment mapFrag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.food_trucks_food_truck, container, false);
        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.food_trucks));
        BottomBarButtonClickState buttonClickState = (BottomBarButtonClickState) Activity;
        buttonClickState.FindStationButton();

        ((MainActivity) getActivity()).search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Holder holder;
                holder = new ViewHolder(R.layout.search_sheet);
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
                                        break;
                                }
                            }
                        })
                        .create();
                dialog.show();
            }
        });

        defineView();

        return v;
    }

    private void defineView() {

        food_truck_recy = v.findViewById(R.id.food_truck_recy);
        linearLayoutManager = new LinearLayoutManager(Activity);
        food_truck_recy.setLayoutManager(linearLayoutManager);

        mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        fillFoodTrucksData();

    }

    private void fillFoodTrucksData() {
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
        food_truck_recy.setAdapter(stationItemAdapter);

        stationItemAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                appConstants.rest_image = stationtList.get(position).getImage();
                appConstants.rest_id = stationtList.get(position).getId();
                appConstants.rest_name=stationtList.get(position).getName();
                appConstants.rest_cusione = stationtList.get(position).getCusine();
                appConstants.rest_rate = stationtList.get(position).getRate();
                appConstants.rest_isFavorite = stationtList.get(position).isFavorite();

                Activity.pushFragments(new TrucksMenueFragment(), true, true);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (mGoogleMap != null) {
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    new LatLng(29.375442, 47.991212)).zoom(13).build();

            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }
}
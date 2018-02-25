package com.mawaqaa.trucky.Fragement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mawaqaa.trucky.Activity.MainActivity;
import com.mawaqaa.trucky.Adapter.MenuItemAdapter;
import com.mawaqaa.trucky.Models.MenuItemModel;
import com.mawaqaa.trucky.OtherClasses.appConstants;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.interfaces.BottomBarButtonClickState;

import java.util.ArrayList;
import java.util.List;

public class RestaurentMenuFragment extends MainBaseFragment implements View.OnClickListener {
    private static String TAG = "RestaurentMenu";
    View v;
    TextView rest_name, cusine_typ_txt, live_station_num, truck_num;
    ImageView rest_img, fav_img;
    RatingBar rest_rate;
    RecyclerView truck_recy, station_recy;
    private LinearLayoutManager linearLayoutManager , linearLayoutManager2;

    List<MenuItemModel> truckList = new ArrayList<MenuItemModel>();
    List<MenuItemModel> stationList = new ArrayList<MenuItemModel>();

    MenuItemAdapter truckItemAdapter ;
    MenuItemAdapter stationItemAdapter ;

    boolean trucky_is_open = false ;
    boolean station_is_open = false ;

    Button truck_btn ,station_btn ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.resturent_menu));

        BottomBarButtonClickState buttonClickState = (BottomBarButtonClickState) Activity;
        buttonClickState.ResturentButton();

        v = inflater.inflate(R.layout.restaurent_menu_fragment, container, false);

        defineView(); defineView();
        return v;
    }

    private void defineView() {
        rest_name = v.findViewById(R.id.rest_name);
        cusine_typ_txt = v.findViewById(R.id.cusine_typ_txt);
        live_station_num = v.findViewById(R.id.live_station_num);
        truck_num = v.findViewById(R.id.truck_num);
        truck_btn= v.findViewById(R.id.truck_btn);
        station_btn= v.findViewById(R.id.station_btn);


        rest_img = v.findViewById(R.id.rest_img);
        fav_img = v.findViewById(R.id.fav_img);
        rest_rate = v.findViewById(R.id.rest_rate);

        truck_recy = v.findViewById(R.id.truck_recy);
        station_recy = v.findViewById(R.id.station_recy);
        linearLayoutManager = new LinearLayoutManager(Activity);
        linearLayoutManager2 = new LinearLayoutManager(Activity);
        truck_recy.setLayoutManager(linearLayoutManager);
        station_recy.setLayoutManager(linearLayoutManager2);

        rest_name.setText(appConstants.rest_name);
        cusine_typ_txt.setText(appConstants.rest_cusione);
        live_station_num.setText("" + appConstants.rest_station);
        truck_num.setText("" + appConstants.rest_truck);

        if (appConstants.rest_isFavorite) {
            fav_img.setImageResource(R.drawable.favourite);
        } else {
            fav_img.setImageResource(R.drawable.favourite2);
        }

        rest_rate.setRating(Float.parseFloat(String.valueOf(appConstants.rest_rate)));

        Glide.with(Activity)
                .load(appConstants.rest_image)
                .apply(RequestOptions.circleCropTransform())
                .into(rest_img);

        fillTruckData();

        station_btn.setOnClickListener(this);
        truck_btn.setOnClickListener(this);

    }

    private void fillTruckData() {
        truckList.clear();
        for (int i = 0 ; i<2 ; i++){
            truckList.add(new MenuItemModel(
                    0 ,
                    "Burger",
                    "https://images.fastcompany.net/image/upload/w_596,c_limit,q_auto:best,f_auto,fl_lossy/wp-cms/uploads/2017/06/i-1-sonic-burger.jpg",
                    "Fat",
                    "1",
                    false
            ));
        }

        truckItemAdapter = new MenuItemAdapter(Activity,truckList);
        truck_recy.setAdapter(truckItemAdapter);

        fillStation();
    }

    private void fillStation() {
        stationList.clear();

        for (int i = 0 ; i<2 ; i++){
            stationList.add(new MenuItemModel(
                    0 ,
                    "Burger",
                    "https://images.fastcompany.net/image/upload/w_596,c_limit,q_auto:best,f_auto,fl_lossy/wp-cms/uploads/2017/06/i-1-sonic-burger.jpg",
                    "Fat",
                    "1",
                    false
            ));
        }

        stationItemAdapter = new MenuItemAdapter(Activity,stationList);
        station_recy.setAdapter(stationItemAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.truck_btn :
                if (trucky_is_open){
                    trucky_is_open = false  ;
                    truck_recy.setVisibility(View.GONE);
                }else {
                    trucky_is_open = true  ;
                    truck_recy.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.station_btn:
                if (station_is_open){
                    station_is_open = false  ;
                    station_recy.setVisibility(View.GONE);
                }else {
                    station_is_open = true  ;
                    station_recy.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}

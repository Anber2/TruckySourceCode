package com.mawaqaa.trucky.Fragement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mawaqaa.trucky.Adapter.StationMenuAdapter;
import com.mawaqaa.trucky.Models.stationMenuModel;
import com.mawaqaa.trucky.OtherClasses.appConstants;
import com.mawaqaa.trucky.R;

import java.util.ArrayList;
import java.util.List;

public class StationMenuFragment extends MainBaseFragment {

    View v ;
    RecyclerView station_menu ;
    private LinearLayoutManager linearLayoutManager ;

    TextView rest_name, cusine_typ_txt;
    ImageView rest_img, fav_img;
    RatingBar rest_rate;

    StationMenuAdapter stationMenuAdapter ;


    List<stationMenuModel> stationMenuList = new ArrayList<stationMenuModel>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.station_menu_fragment, container, false);

        defineView();
        return v ;
    }

    private void defineView() {
        station_menu = v.findViewById(R.id.station_menu);
        linearLayoutManager = new LinearLayoutManager(Activity);
        station_menu.setLayoutManager(linearLayoutManager);

        rest_name = v.findViewById(R.id.rest_name);
        cusine_typ_txt = v.findViewById(R.id.cusine_typ_txt);
        rest_img = v.findViewById(R.id.rest_img);
        fav_img = v.findViewById(R.id.fav_img);
        rest_rate = v.findViewById(R.id.rest_rate);

        rest_name.setText(appConstants.rest_name);
        cusine_typ_txt.setText(appConstants.rest_cusione);

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

        fillMenuData();
    }

    private void fillMenuData() {
        stationMenuList.clear();
        for (int i = 0 ; i < 5 ; i++){
            stationMenuList.add(new stationMenuModel(
                    1,
                    "PASTA STATION" ,
                    "http://www.seriouseats.com/recipes/images/2016/08/20160827-cherry-tomato-pasta-13-1500x1125.jpg",
                    "25 Free Pasta",
                    "20",
                    3));
        }

        stationMenuAdapter = new StationMenuAdapter(Activity , stationMenuList);
        station_menu.setAdapter(stationMenuAdapter);

        stationMenuAdapter.setOnItemClickListener(new StationMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                appConstants.item_image = stationMenuList.get(position).getItem_Image();
                appConstants.item_id = stationMenuList.get(position).getItem_id();
                appConstants.item_name=stationMenuList.get(position).getItem_Name();
                appConstants.item_cusione = stationMenuList.get(position).getItem_Cusine();
                appConstants.item_price = stationMenuList.get(position).getItem_price();
                appConstants.item_services = stationMenuList.get(position).getItem_services();

                Activity.pushFragments(new ItemDetailsFragment(), true, true);
            }
        });
    }
}
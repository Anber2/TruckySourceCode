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
import com.mawaqaa.trucky.Adapter.TruckItemMenuAdapter;
import com.mawaqaa.trucky.Models.truckItemMenuModel;
import com.mawaqaa.trucky.OtherClasses.appConstants;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.interfaces.BottomBarButtonClickState;

import java.util.ArrayList;
import java.util.List;

public class TrucksMenueFragment extends MainBaseFragment implements View.OnClickListener {

    View v;

    Button customized_food  ;

    private static String TAG = "TrucksMenueFrag";
    TextView rest_name, cusine_typ_txt;
    ImageView rest_img, fav_img;
    RatingBar rest_rate;
    RecyclerView truck_recy;
    private LinearLayoutManager linearLayoutManager;

    List<truckItemMenuModel> truckList = new ArrayList<truckItemMenuModel>();

//    MenuItemAdapter truckItemAdapter;
    TruckItemMenuAdapter truckItemMenuAdapter;

    boolean trucky_is_open = false;
    boolean station_is_open = false;

    Button truck_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.trucks_menue_fragment, container, false);

        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.trucks_menu));

        BottomBarButtonClickState buttonClickState = (BottomBarButtonClickState) Activity;
        buttonClickState.ResturentButton();

        defineView();

        return v;
    }

    private void defineView() {
        rest_name = v.findViewById(R.id.rest_name);
        cusine_typ_txt = v.findViewById(R.id.cusine_typ_txt);
        truck_btn = v.findViewById(R.id.truck_btn);
        customized_food = v.findViewById(R.id.customized_food);


        rest_img = v.findViewById(R.id.rest_img);
        fav_img = v.findViewById(R.id.fav_img);
        rest_rate = v.findViewById(R.id.rest_rate);

        truck_recy = v.findViewById(R.id.truck_recy);
        linearLayoutManager = new LinearLayoutManager(Activity);
        truck_recy.setLayoutManager(linearLayoutManager);

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

        fillTruckData();

        truck_btn.setOnClickListener(this);
        customized_food.setOnClickListener(this);
    }

    private void fillTruckData() {
        truckList.clear();
        for (int i = 0; i < 2; i++) {

            truckList.add(new truckItemMenuModel(
                    0,
                    "Burger",
                    "https://images.fastcompany.net/image/upload/w_596,c_limit,q_auto:best,f_auto,fl_lossy/wp-cms/uploads/2017/06/i-1-sonic-burger.jpg",
                    "Burger",
                    "20.5",
                    "20"
            ));
        }

        truckItemMenuAdapter = new TruckItemMenuAdapter(Activity, truckList);
        truck_recy.setAdapter(truckItemMenuAdapter);

        truckItemMenuAdapter.setOnItemClickListener(new TruckItemMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                appConstants.item_image = truckList.get(position).getImage();
                appConstants.item_id = truckList.get(position).getId();
                appConstants.item_name=truckList.get(position).getName();
                appConstants.item_price = truckList.get(position).getPrice();
                appConstants.item_time_to_finish = truckList.get(position).getTime_t_finish();

                Activity.pushFragments(new ItemTruckDetailsFragment(), true, true);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.truck_btn:
                if (trucky_is_open) {
                    trucky_is_open = false;
                    truck_recy.setVisibility(View.GONE);
                } else {
                    trucky_is_open = true;
                    truck_recy.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.customized_food:
                Activity.pushFragments(new CustomizedFoodFragment(),false,true);
                break;
        }
    }
}
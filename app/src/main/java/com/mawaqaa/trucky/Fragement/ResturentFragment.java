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
import com.mawaqaa.trucky.Adapter.ResturentAdapter;
import com.mawaqaa.trucky.Models.resturentModel;
import com.mawaqaa.trucky.OtherClasses.appConstants;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.interfaces.BottomBarButtonClickState;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ResturentFragment extends MainBaseFragment {
    View v;
    RecyclerView resturent_recy;
    private LinearLayoutManager linearLayoutManager;
    List<resturentModel> restaurentList = new ArrayList<resturentModel>();

    int[] id = {1, 2, 3};
    String[] name = {" KFC ", "Second Resturent", "6abe5"};
    String[] cusine_type = {"Italian", "Indian", "Spanish"};
    String[] rest_rate = {"3.5", "3.4", "4.0"};
    int[] truke = {1, 5, 6};
    int[] live_station = {2, 5, 8};
    boolean[] is_favorite = {true, false, false};

    String[] rest_img = {
            "https://botw-pd.s3.amazonaws.com/styles/logo-thumbnail/s3/0016/2961/brand.gif?itok=_noyL-1W",
            "http://al-feel.com/logo.jpg",
            "http://www.jeddahfood.com/wp-content/gallery/blue-ocean/Blue-Ocean-Menu-oct-2015-rev2-1.jpg"
    };
    ResturentAdapter resturentAdapter;
    Spinner location , cuisine_type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.resturent_fragment, container, false);
        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.resturent));

        BottomBarButtonClickState buttonClickState = (BottomBarButtonClickState) Activity;
        buttonClickState.ResturentButton();

        resturent_recy = v.findViewById(R.id.resturent_recy);
        linearLayoutManager = new LinearLayoutManager(Activity);
        resturent_recy.setLayoutManager(linearLayoutManager);

        ((MainActivity) getActivity()).search_btn.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
//                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                } else {
//                    sheetBehavior.setState(BottomSheetBeh avior.STATE_COLLAPSED);
//                }
                Holder holder;
                holder = new ViewHolder(R.layout.resturent_top_dialog);
                final DialogPlus dialog = DialogPlus.newDialog(getContext())
                        .setContentHolder(holder)
                        .setCancelable(true)
                        .setGravity(Gravity.TOP)
                        .setBackgroundColorResId(Color.TRANSPARENT)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(DialogPlus dialog, View view) {
                                switch (view.getId()) {
                                    case R.id.find:
                                        dialog.dismiss();
//                                        Activity.pushFragments(new FoodTrucksFragment(), true, true);
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

        fillData();

        return v;
    }

    private void fillData() {
        restaurentList.clear();

        for (int i = 0; i < id.length; i++) {
            restaurentList.add(new resturentModel(id[i], name[i], rest_img[i], cusine_type[i], rest_rate[i], truke[i], live_station[i], is_favorite[i]));
        }
        resturentAdapter = new ResturentAdapter(Activity, restaurentList);
        resturent_recy.setAdapter(resturentAdapter);

        resturentAdapter.setOnItemClickListener(new ResturentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                appConstants.rest_image = restaurentList.get(position).getRest_img();
                appConstants.rest_id = restaurentList.get(position).getID();
                appConstants.rest_name=restaurentList.get(position).getName();
                appConstants.rest_cusione = restaurentList.get(position).getCusine_type();
                appConstants.rest_rate = restaurentList.get(position).getRest_rate();
                appConstants.rest_isFavorite = restaurentList.get(position).isIs_favorite();
                appConstants.rest_truck = restaurentList.get(position).getTruke();
                appConstants.rest_station = restaurentList.get(position).getLive_station();

                Activity.pushFragments(new RestaurentMenuFragment(), true, true);
            }
        });
    }
}
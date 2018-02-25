package com.mawaqaa.trucky.Fragement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mawaqaa.trucky.Activity.MainActivity;
import com.mawaqaa.trucky.Adapter.CustomizedFoodAdapter;
import com.mawaqaa.trucky.Adapter.MenuItemAdapter;
import com.mawaqaa.trucky.Models.CustomizedFoodModel;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.interfaces.BottomBarButtonClickState;

import java.util.ArrayList;
import java.util.List;

public class CustomizedFoodFragment extends MainBaseFragment implements View.OnClickListener {

    View v;
    RecyclerView standard_recy;
    Button add_to_bag;
    List<CustomizedFoodModel> FoodList = new ArrayList<CustomizedFoodModel>();
    MenuItemAdapter stationItemAdapter;
    String[] nameFood = {"Burger", "Pizza"};
    CustomizedFoodAdapter myCustomizedFoodAdapter ;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_customized_food, container, false);
        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.customized_food));
        BottomBarButtonClickState buttonClickState = (BottomBarButtonClickState) Activity;
        buttonClickState.FindStationButton();

        standard_recy = v.findViewById(R.id.standard_recy);
        add_to_bag = v.findViewById(R.id.add_to_bag);
        linearLayoutManager = new LinearLayoutManager(Activity);
        standard_recy.setLayoutManager(linearLayoutManager);

        fillcustimizedData();

        add_to_bag.setOnClickListener(this);
        return v;
    }

    private void fillcustimizedData() {
        FoodList.clear();
        for (int i = 0 ; i < 2 ; i++){
            FoodList.add(new CustomizedFoodModel(
                    1,
                    nameFood[i]
            ));

        }

        myCustomizedFoodAdapter= new CustomizedFoodAdapter(Activity , FoodList);
        standard_recy.setAdapter(myCustomizedFoodAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_to_bag:
                Activity.pushFragments(new MyCartFragment(),false , true);
                break;
        }
    }
}
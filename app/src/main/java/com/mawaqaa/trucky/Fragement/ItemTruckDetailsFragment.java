package com.mawaqaa.trucky.Fragement;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mawaqaa.trucky.OtherClasses.appConstants;
import com.mawaqaa.trucky.R;

public class ItemTruckDetailsFragment extends MainBaseFragment implements View.OnClickListener {

    ImageView item_img, special_req_arrow, minus_services, plus_services;
    TextView price_txt, item_name_txt, services_txt, service_presentation_txt, services_num;
    View v;
    RecyclerView food_desc_recy;
    RelativeLayout gender_rel_layout, special_req_rel_layout;
    EditText special_req_edt;
    static boolean isSpecialReqOpen = false;
    static int ser_num = 0;
    Button add_to_bag ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.item_truck_details_fragment, container, false);
        defineView();
        return v ;
    }

    private void defineView() {
        item_img = v.findViewById(R.id.item_img);
        Glide.with(Activity)
                .load(appConstants.item_image)
                .into(item_img);
        services_num = v.findViewById(R.id.services_num);
        special_req_rel_layout = v.findViewById(R.id.special_req_rel_layout);
        special_req_edt = v.findViewById(R.id.special_req_edt);
        services_num.setText(""+ser_num);

        minus_services = v.findViewById(R.id.minus_services);
        plus_services = v.findViewById(R.id.plus_services);
        special_req_arrow = v.findViewById(R.id.special_req_arrow);
        add_to_bag = v.findViewById(R.id.add_to_bag);

        add_to_bag.setOnClickListener(this);
        minus_services.setOnClickListener(this);
        plus_services.setOnClickListener(this);
        special_req_rel_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.special_req_rel_layout:
                if (isSpecialReqOpen) {
                    isSpecialReqOpen = false;
                    special_req_edt.setVisibility(View.GONE);
                    special_req_arrow.setImageResource(R.drawable.dwn_arrow);
                } else {
                    isSpecialReqOpen = true;
                    special_req_edt.setVisibility(View.VISIBLE);
                    special_req_arrow.setImageResource(R.drawable.up_arrow);
                }
                break;

            case R.id.minus_services:
                if (ser_num == 0 ){

                }else{
                    -- ser_num ;
                    services_num.setText(""+ser_num);
                }
                break;

            case R.id.plus_services:
                ++ ser_num ;
                services_num.setText(""+ser_num);
                break;

            case R.id.add_to_bag:
                Activity.pushFragments(new MyCartFragment(),false , true);
                break;
        }
    }
}
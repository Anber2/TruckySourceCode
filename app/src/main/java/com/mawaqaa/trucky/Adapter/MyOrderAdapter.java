package com.mawaqaa.trucky.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mawaqaa.trucky.Models.MyOrderModel;
import com.mawaqaa.trucky.R;

import java.util.List;

/**
 * Created by Ayadi on 2/11/2018.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrderViewHolder> {

    Context context;
    List<MyOrderModel> myOrderList;

    public MyOrderAdapter(Context context, List<MyOrderModel> myOrderList) {
        this.context = context;
        this.myOrderList = myOrderList;

    }

    @Override
    public MyOrderAdapter.MyOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item, parent, false);
        MyOrderViewHolder viewholder = new MyOrderViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyOrderAdapter.MyOrderViewHolder holder, int position) {

        final MyOrderModel info = myOrderList.get(position);

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class MyOrderViewHolder extends RecyclerView.ViewHolder  {

        TextView rest_name , cusine_typ_txt , live_station_num ,truck_num ;
        ImageView rest_img ,fav_img;
        RatingBar rest_rate ;
        public MyOrderViewHolder(View itemView) {
            super(itemView);
            rest_name = itemView.findViewById(R.id.rest_name);
            cusine_typ_txt = itemView.findViewById(R.id.cusine_typ_txt);
            live_station_num = itemView.findViewById(R.id.live_station_num);
            truck_num = itemView.findViewById(R.id.truck_num);

            rest_img = itemView.findViewById(R.id.rest_img);
            fav_img = itemView.findViewById(R.id.fav_img);
            rest_rate = itemView.findViewById(R.id.rest_rate);



        }


    }



}

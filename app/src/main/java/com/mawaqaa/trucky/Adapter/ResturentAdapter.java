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
import com.bumptech.glide.request.RequestOptions;
import com.mawaqaa.trucky.Models.resturentModel;
import com.mawaqaa.trucky.R;

import java.util.List;

/**
 * Created by Ayadi on 2/6/2018.
 */

public class ResturentAdapter extends RecyclerView.Adapter<ResturentAdapter.ResturentViewHolder> {

    Context context;
    List<resturentModel> ResourcelList;
    static OnItemClickListener mItemClickListener;

    public ResturentAdapter(Context context, List<resturentModel> resourcelList) {
        this.context = context;
        ResourcelList = resourcelList;

    }

    @Override
    public ResturentAdapter.ResturentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resturent_item, parent, false);
        ResturentViewHolder viewholder = new ResturentViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ResturentAdapter.ResturentViewHolder holder, int position) {
        final resturentModel info = ResourcelList.get(position);

        holder.rest_name.setText(info.getName());
        holder.cusine_typ_txt.setText(info.getCusine_type());
        holder.live_station_num.setText(""+info.getLive_station());
        holder.truck_num.setText(""+info.getTruke());

        if (info.isIs_favorite()){
            holder.fav_img.setImageResource(R.drawable.favourite);
        }else{
            holder.fav_img.setImageResource(R.drawable.favourite2);
        }

        holder.rest_rate.setRating(Float.parseFloat(String.valueOf(info.getRest_rate())));

        Glide.with(context)
                .load( info.getRest_img())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.rest_img);
    }

    @Override
    public int getItemCount() {
        return ResourcelList.size();
    }

    public static class ResturentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView rest_name , cusine_typ_txt , live_station_num ,truck_num ;
        ImageView rest_img ,fav_img;
        RatingBar rest_rate ;
        public ResturentViewHolder(View itemView) {
            super(itemView);
            rest_name = itemView.findViewById(R.id.rest_name);
            cusine_typ_txt = itemView.findViewById(R.id.cusine_typ_txt);
            live_station_num = itemView.findViewById(R.id.live_station_num);
            truck_num = itemView.findViewById(R.id.truck_num);

            rest_img = itemView.findViewById(R.id.rest_img);
            fav_img = itemView.findViewById(R.id.fav_img);
            rest_rate = itemView.findViewById(R.id.rest_rate);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    public void setOnItemClickListener(final ResturentAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

}

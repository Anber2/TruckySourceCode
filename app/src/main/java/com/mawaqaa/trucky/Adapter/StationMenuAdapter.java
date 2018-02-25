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
import com.mawaqaa.trucky.Models.stationMenuModel;
import com.mawaqaa.trucky.R;

import java.util.List;

/**
 * Created by Ayadi on 2/8/2018.
 */

public class StationMenuAdapter extends RecyclerView.Adapter<StationMenuAdapter.StationMenuItemViewHolder> {

    Context context;
    List<stationMenuModel> ResourcelList;
    static OnItemClickListener mItemClickListener;

    public StationMenuAdapter(Context context, List<stationMenuModel> resourcelList) {
        this.context = context;
        ResourcelList = resourcelList;

    }

    @Override
    public StationMenuAdapter.StationMenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_menu_item_row, parent, false);
        StationMenuItemViewHolder viewholder = new StationMenuItemViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(StationMenuAdapter.StationMenuItemViewHolder holder, int position) {
        final stationMenuModel info = ResourcelList.get(position);

        holder.rest_name.setText(info.getItem_Name());
        holder.cusine_typ_txt.setText(info.getItem_Cusine());

        holder.price_txt.setText(info.getItem_price() + " KD ");
        holder.services_txt.setText(""+info.getItem_services());

//        holder.rest_rate.setRating(Float.parseFloat(String.valueOf(info.getRate())));

        Glide.with(context)
                .load( info.getItem_Image())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.rest_img);
    }

    @Override
    public int getItemCount() {
        return ResourcelList.size();
    }

    public static class StationMenuItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView rest_name , cusine_typ_txt , services_txt , price_txt ;
        ImageView rest_img ,fav_img;
        RatingBar rest_rate ;
        public StationMenuItemViewHolder(View itemView) {
            super(itemView);
            rest_name = itemView.findViewById(R.id.rest_name);
            cusine_typ_txt = itemView.findViewById(R.id.cusine_typ_txt);


            rest_img = itemView.findViewById(R.id.rest_img);
            fav_img = itemView.findViewById(R.id.fav_img);
//            rest_rate = itemView.findViewById(R.id.rest_rate);
            price_txt = itemView.findViewById(R.id.price_txt);
            services_txt = itemView.findViewById(R.id.services_txt);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }
    public void setOnItemClickListener(final StationMenuAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}

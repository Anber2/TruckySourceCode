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
import com.mawaqaa.trucky.Models.truckItemMenuModel;
import com.mawaqaa.trucky.R;

import java.util.List;

/**
 * Created by Ayadi on 2/14/2018.
 */

public class TruckItemMenuAdapter extends RecyclerView.Adapter<TruckItemMenuAdapter.MenuItemViewHolder> {

    Context context;
    List<truckItemMenuModel> ResourcelList;
    static OnItemClickListener mItemClickListener;

    public TruckItemMenuAdapter(Context context, List<truckItemMenuModel> resourcelList) {
        this.context = context;
        ResourcelList = resourcelList;

    }

    @Override
    public TruckItemMenuAdapter.MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.truck_menu_item_row, parent, false);
        MenuItemViewHolder viewholder = new MenuItemViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(TruckItemMenuAdapter.MenuItemViewHolder holder, int position) {
        final truckItemMenuModel info = ResourcelList.get(position);

        holder.rest_name.setText(info.getName());
        holder.cusine_typ_txt.setText(info.getDesc());





        Glide.with(context)
                .load( info.getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.rest_img);
    }

    @Override
    public int getItemCount() {
        return ResourcelList.size();
    }

    public static class MenuItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView rest_name , cusine_typ_txt ;
        ImageView rest_img ,fav_img;
        RatingBar rest_rate ;
        public MenuItemViewHolder(View itemView) {
            super(itemView);
            rest_name = itemView.findViewById(R.id.rest_name);
            cusine_typ_txt = itemView.findViewById(R.id.cusine_typ_txt);

            rest_img = itemView.findViewById(R.id.rest_img);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    public void setOnItemClickListener(final TruckItemMenuAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

}

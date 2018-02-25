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
import com.mawaqaa.trucky.Models.MenuItemModel;
import com.mawaqaa.trucky.R;

import java.util.List;

/**
 * Created by Ayadi on 2/6/2018.
 */

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder> {

    Context context;
    List<MenuItemModel> ResourcelList;
    static OnItemClickListener mItemClickListener;

    public MenuItemAdapter(Context context, List<MenuItemModel> resourcelList) {
        this.context = context;
        ResourcelList = resourcelList;

    }

    @Override
    public MenuItemAdapter.MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_row, parent, false);
        MenuItemViewHolder viewholder = new MenuItemViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(MenuItemAdapter.MenuItemViewHolder holder, int position) {
        final MenuItemModel info = ResourcelList.get(position);

        holder.rest_name.setText(info.getName());
        holder.cusine_typ_txt.setText(info.getCusine());


        if (info.isFavorite()){
            holder.fav_img.setImageResource(R.drawable.favourite);
        }else{
            holder.fav_img.setImageResource(R.drawable.favourite2);
        }

        holder.rest_rate.setRating(Float.parseFloat(String.valueOf(info.getRate())));

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

    public void setOnItemClickListener(final MenuItemAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

}

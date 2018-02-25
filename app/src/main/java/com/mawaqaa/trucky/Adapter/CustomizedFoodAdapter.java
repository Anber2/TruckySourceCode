package com.mawaqaa.trucky.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mawaqaa.trucky.Models.CustomizedFoodModel;
import com.mawaqaa.trucky.R;

import java.util.List;

/**
 * Created by Ayadi on 2/15/2018.
 */

public class CustomizedFoodAdapter extends RecyclerView.Adapter<CustomizedFoodAdapter.CustomizedFoodViewHolder> {

    Context context;
    List<CustomizedFoodModel> ResourcelList;

    public CustomizedFoodAdapter(Context context, List<CustomizedFoodModel> resourcelList) {
        this.context = context;
        ResourcelList = resourcelList;

    }

    @Override
    public CustomizedFoodAdapter.CustomizedFoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customized_food_item, parent, false);
        CustomizedFoodViewHolder viewholder = new CustomizedFoodViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(CustomizedFoodAdapter.CustomizedFoodViewHolder holder, int position) {
        final CustomizedFoodModel info = ResourcelList.get(position);
        holder.food_type.setText(info.getNameFood());
    }

    @Override
    public int getItemCount() {
        return ResourcelList.size();
    }

    public static class CustomizedFoodViewHolder extends RecyclerView.ViewHolder {

        TextView food_type;

        public CustomizedFoodViewHolder(View itemView) {
            super(itemView);
            food_type = itemView.findViewById(R.id.food_type);


        }
    }
}